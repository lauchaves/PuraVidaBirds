package cr.ac.itcr.examen1;

/**
 * Created by Laurens on 09/04/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter
{
    private static final String DATABASE_TABLE = "USERS";
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_USERNAME = "user_email";
    public static final String KEY_PASSWORD = "user_password";

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

    public long register(String user,String pw)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, user);
        initialValues.put(KEY_PASSWORD, pw);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
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
}