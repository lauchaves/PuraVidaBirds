package cr.ac.itcr.puravidabirds.access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cr.ac.itcr.puravidabirds.entitys.Bird;

/**
 * Created by Laurens on 12/04/2016.
 */
public class BirdRepo implements IRepositoryBird<Bird> {
    private DBHelper connexion;

    public BirdRepo(Context context) {
        connexion = new DBHelper(context);
    }

    @Override
    public boolean Save(Bird bird) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                ContentValues newValues = new ContentValues();
                newValues.put("birdName", bird.getBirdName());
                newValues.put("birdScientificName", bird.getBirdScientificName());
                newValues.put("imagePath", String.valueOf(bird.getImagePath()));

                db.insert("BIRDS", null, newValues);

                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return true;
    }

    @Override
    public boolean Update(Bird bird) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null) {

                ContentValues updateData = new ContentValues();
                updateData.put("birdName", bird.getBirdName());
                updateData.put("birdScientificName", bird.getBirdScientificName());

                db.update("BIRDS", updateData, "_id=?", new String[]{String.valueOf(bird.getId())});

                connexion.close();
                return false;
            }
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return true;
    }

    @Override
    public boolean Delete(Bird bird) {

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                String[] args = new String[]{String.valueOf(bird.getId())};

                db.delete("BIRDS", "_id=?", args);

                connexion.close();
                return false;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return true;
    }

    @Override
    public ArrayList<String> GetAll() {

        ArrayList<String> listPlace = new ArrayList<String>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                Cursor cursor = db.query("BIRDS", new String[]{"birdName","birdScientificName","imagePath"}, null,
                        null, null, null, "_id desc", null);

                if(cursor.moveToFirst()){
                    do{

                        String nombre = cursor.getString(0);

                        Log.d("Nombre:", nombre);

                        listPlace.add(nombre);
                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listPlace;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listPlace;
    }


    @Override
    public ArrayList<Bird> GetBy(Bird place) {

        ArrayList<Bird> listPlace = new ArrayList<Bird>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                String[] args = new String[]{String.valueOf(place.getId())};
                Cursor cursor = db.query("BIRDS", new String[]{"_id", "birdName","birdScientificName","imagePath"}, "_id=?",
                        args, null, null, "_id desc", null);

                if(cursor.moveToFirst()){
                    do{
                        int id = cursor.getInt(0);
                        String nombre = cursor.getString(1);
                        String scientific = cursor.getString(2);
                        String img = cursor.getString(3);

                        Bird bird = new Bird();
                        bird.setId(id);
                        bird.setBirdName(nombre);
                        bird.setBirdScientificName(scientific);
                        bird.setImagePath(img);

                        listPlace.add(bird);
                    }while (cursor.moveToNext());
                }

                connexion.close();
                return listPlace;
            }
        }

        catch (Exception error){
            Log.d("error", error.getMessage());
        }

        return listPlace;
    }


    @Override
    public Bird GetByName(String bird) {
        Bird birdaux = new Bird();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                String[] args = new String[]{bird};
                Cursor cursor = db.query("BIRDS",new String[]{"_id", "birdName","birdScientificName","imagePath"},"birdName=?",args,null,null,"birdName desc",null);
                if (cursor.moveToFirst()){


                    String id = cursor.getString(0);
                    String birdnameaux = cursor.getString(0);
                    String namescientificaux = cursor.getString(1);
                    String img = cursor.getString(2);

                    birdaux.setId(Integer.parseInt(id));
                    birdaux.setBirdName(birdnameaux);
                    birdaux.setBirdScientificName(namescientificaux);

                }
                connexion.close();
            }
        }
        catch (Exception error){
            Log.d("error",error.getMessage());
        }
        return birdaux;
    }
}



