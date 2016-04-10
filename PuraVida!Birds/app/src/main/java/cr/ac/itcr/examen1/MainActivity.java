package cr.ac.itcr.examen1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
     * Declaramos el controlador de la BBDD y accedemos en modo escritura
     */
        DBHelper dbHelper = new DBHelper(getBaseContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Toast.makeText(MainActivity.this, "DataBase Prepared", Toast.LENGTH_LONG);

    }


    

}
