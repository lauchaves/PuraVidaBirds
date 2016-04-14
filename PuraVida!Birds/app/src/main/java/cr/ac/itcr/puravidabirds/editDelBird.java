package cr.ac.itcr.puravidabirds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.itcr.puravidabirds.access_data.BirdRepo;
import cr.ac.itcr.puravidabirds.access_data.IRepositoryBird;
import cr.ac.itcr.puravidabirds.entitys.Bird;

/**
 * Created by Laurens on 13/04/2016.
 */
public class editDelBird extends AppCompatActivity {
    EditText birdname;
    EditText birdscientific;
    Button btn_edit;
    Button btn_del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editbird);

        String mybirdname = getIntent().getExtras().getString("birdname");
        String birdScientific = getIntent().getExtras().getString("birdScientific");
        final String idaux = (String) getIntent().getExtras().get("idaaa");


       /*final IRepositoryBird repository = new BirdRepo(this);

        Bird b = repository.GetByName(mybirdname);
        String bname = b.getBirdName();
        String bscientific = b.getBirdScientificName();*/


        birdname = (EditText) findViewById(R.id.input_nameBirdasd);
        birdscientific = (EditText) findViewById(R.id.input_birdScientificNameasd);

        birdname.setText(mybirdname);
        birdscientific.setText(birdScientific);

        btn_del= (Button)findViewById(R.id.btn_delete);
         btn_edit= (Button)findViewById(R.id.btn_edit);

        // Se le da funcionalidad al boton de Editar
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (birdname.getText().toString().equals("")) {
                    birdname.setError("at least 3 characters");
                } else {
                    // una vez que el usuario cambie algo y de click en el boton  se crea un objeto Flor
                    Bird bird = new Bird();
                    bird.setId(Integer.parseInt(idaux));
                    bird.setBirdName(birdname.getText().toString());
                    bird.setBirdScientificName(birdscientific.getText().toString());

                    IRepositoryBird repository = new BirdRepo(getApplicationContext());
                    //Y luego se modifican los campos por medio de la función Update
                    repository.Update(bird);

                    Toast.makeText(getApplicationContext(), "Successfully Edited", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });
        //Se le da funcionalidada al boton eliminar
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (birdname.getText().toString().equals("")) {
                    birdname.setError("at least 3 characters");
                } else {
                    // Se crea un objeto con los atributos  que estan en los campos
                    Bird bird = new Bird();
                    bird.setId(Integer.parseInt(idaux));;
                    bird.setBirdName(birdname.getText().toString());
                    bird.setBirdScientificName(birdscientific.getText().toString());

                    //Se limbian los editText
                    birdname.setText("");
                    birdscientific.setText("");

                    IRepositoryBird repository = new BirdRepo(getApplicationContext());
                    repository.Delete(bird);

                    Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });

    }

}
