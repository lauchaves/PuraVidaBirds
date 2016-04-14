package cr.ac.itcr.puravidabirds.access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Laurens on 09/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "PuraVidaDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public DBHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creating Data Base: PuraVidaDb");

        db.execSQL("CREATE TABLE USERS(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " user_name TEXT NOT NULL, " +
                " user_email TEXT NOT NULL, " +
                " user_password TEXT )");

        db.execSQL( "CREATE TABLE BIRDS(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " birdName TEXT NOT NULL, " +
                " birdScientificName TEXT NOT NULL, " +
                " imagePath TEXT )" );

        db.execSQL( "CREATE UNIQUE INDEX user_name ON USERS(user_name ASC)" );

        Log.i(this.getClass().toString(), "Table USERS created");
        Log.i(this.getClass().toString(), "Table BIRDS created");

   /*
    * Initial data
    */
        db.execSQL("INSERT INTO USERS(user_name,user_email,user_password) VALUES('Laurens Chaves','laurens004@gmail.com','12345')");
        db.execSQL("INSERT INTO USERS(user_name,user_email,user_password) VALUES('Kevin Walsh', 'kevin@gmail.com','12345')");
        db.execSQL("INSERT INTO USERS(user_name,user_email,user_password) VALUES('Eder Naranjo','eder@gmail.com','12345')");
        db.execSQL("INSERT INTO USERS(user_name,user_email,user_password) VALUES('Brian Salazar','brian@gmail.com','12345')");


        Log.i(this.getClass().toString(), " Initial data USERS inserted");

        Log.i(this.getClass().toString(), "Data Base: PuraVidaDb Created");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            StringBuilder sql = new StringBuilder();

            for(int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
                int nextVersion = indiceVersion + 1;
                switch (nextVersion){

                    case 1:
                        String sqlDropPlace = "DROP TABLE IF EXISTS USERS";
                        sql.append(sqlDropPlace);
                        break;

                    case 2:
                        String sqlDropPlace2 = "DROP TABLE IF EXISTS BIRDS";
                        sql.append(sqlDropPlace2);
                        break;

                    case 3:

                        break;
                }
            }
            db.execSQL(sql.toString());
        }
        catch (Exception error){

            Log.d("error", error.getMessage());
        }
    }

}
