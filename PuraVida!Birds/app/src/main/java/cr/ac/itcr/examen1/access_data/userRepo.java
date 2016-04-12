package cr.ac.itcr.examen1.access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;

import cr.ac.itcr.examen1.entitys.User;

/**
 * Created by Laurens on 11/04/2016.
 */

public class userRepo  implements IRepositoryUser<User>{
    private DBHelper connexion;

    public userRepo(Context context) {
        connexion = new DBHelper(context);
    }


    @Override
    public boolean Save(User user) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                ContentValues newValues = new ContentValues();
                newValues.put("user_name", user.getName());
                newValues.put("user_email",user.getEmail());
                newValues.put("user_password",user.getPassword());

                db.insert("USERS", null, newValues);

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
    public boolean Update(User user) {

        try{
            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null) {

                ContentValues updateData = new ContentValues();
                updateData.put("user_name", user.getName());

                db.update("USERS", updateData, "_id=?", new String[]{String.valueOf(user.getId())});

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
    public boolean Delete(User user) {

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){
                String[] args = new String[]{String.valueOf(user.getId())};

                db.delete("USERS", "_id=?", args);

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
    public ArrayList<User> GetAll() {

        ArrayList<User> listPlace = new ArrayList<User>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                Cursor cursor = db.query("USERS", new String[]{"_id", "user_name"}, null,
                        null, null, null, "_id desc", null);

                if(cursor.moveToFirst()){
                    do{
                        int id = cursor.getInt(0);
                        String nombre = cursor.getString(1);

                        User user = new User();
                        user.setId(id);
                        user.setName(nombre);
                        Log.d("Nombre:", nombre);


                        listPlace.add(user);
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
    public ArrayList<User> GetBy(User place) {

        ArrayList<User> listPlace = new ArrayList<User>();

        try{

            SQLiteDatabase db = connexion.getWritableDatabase();

            if(db != null){

                String[] args = new String[]{String.valueOf(place.getId())};
                Cursor cursor = db.query("USERS", new String[]{"_id", "user_name"}, "_id=?",
                        args, null, null, "_id desc", null);

                if(cursor.moveToFirst()){
                    do{
                        int id = cursor.getInt(0);
                        String nombre = cursor.getString(1);

                        User placeTemp = new User();
                        placeTemp.setId(id);
                        placeTemp.setName(nombre);

                        listPlace.add(placeTemp);
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

}
