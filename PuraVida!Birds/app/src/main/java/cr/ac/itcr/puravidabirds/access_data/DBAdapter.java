package cr.ac.itcr.puravidabirds.access_data;

/**
 * Created by Laurens on 09/04/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cr.ac.itcr.puravidabirds.R;
import cr.ac.itcr.puravidabirds.entitys.Bird;

public class DBAdapter
{
    private static final String DATABASE_TABLE = "USERS";
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_USERNAME = "user_email";
    public static final String KEY_PASSWORD = "user_password";
    public static String maname;
    public static String miID;


    SQLiteDatabase mDb;
    Context mCtx;
    DBHelper mDbHelper;

    public DBAdapter(Context context)
    {
        this.mCtx = context;
    }

    public DBAdapter open() throws SQLException
    {
        mDbHelper = new DBHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }


    //Funcion de Login
    public Cursor aux(String username) throws SQLException
    {
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + "BIRDS" + " WHERE birdName=? ", new String[]{username});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return mCursor;
            }
        }
        return mCursor;
    }

    //Funcion de Login
    public boolean Login(String username, String password) throws SQLException
    {
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE user_email=? AND user_password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }


    public void getBirdId(String username){
        Cursor cursor = mDb.rawQuery("SELECT _id FROM " + "BIRDS" + " WHERE birdname=?", new String[]{username});
        if (cursor!=null){
            if (cursor.moveToFirst()) {
                miID = cursor.getString(cursor.getColumnIndex("_id"));
            }

            Log.d("bird ID=",miID);

        }

    }

    public void getUserName(String username){
        Cursor cursor = mDb.rawQuery("SELECT user_name FROM " + DATABASE_TABLE + " WHERE user_email=?", new String[]{username});
        if (cursor!=null){
            if (cursor.moveToFirst()) {
                maname = cursor.getString(cursor.getColumnIndex("user_name"));
            }

            Log.d("user Name=",maname);

        }

    }

    // Getting All Shops
    public List<Bird> getAllBirds() {
        List<Bird> shopList = new ArrayList<Bird>();
// Select All Query
        String selectQuery = "SELECT * FROM " + "BIRDS";

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bird bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setBirdName(cursor.getString(1));
                bird.setBirdScientificName(cursor.getString(2));
// Adding contact to list
                shopList.add(bird);
            } while (cursor.moveToNext());
        }

        // return contact list
        return shopList;
    }


    public Cursor birdByName(String id){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + "BIRDS" + " WHERE " + "birdName" + " = " + id + ";";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();

        }
        return c;
    }

    public int findID(String name){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int myId = 0;
        String query = "SELECT _id FROM " + "BIRDS" + " WHERE " + "birdName" + " = " + name + ";";
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
            myId = (Integer.parseInt(c.getString(0)));
        }

        return myId;
        }
    }

    /*//Funcion de SignUp
    public void registerUser(String name,String email, String password) throws SQLException
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("user_name", name);
        newValues.put("user_email",email);
        newValues.put("user_password",password);



        mDb.insert("USERS", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();

    }
    */
