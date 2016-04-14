package cr.ac.itcr.puravidabirds;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cr.ac.itcr.puravidabirds.access_data.DBAdapter;
import cr.ac.itcr.puravidabirds.access_data.IRepositoryUser;
import cr.ac.itcr.puravidabirds.access_data.userRepo;
import cr.ac.itcr.puravidabirds.entitys.User;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    DBAdapter dbAdapter;



    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        // get Instance of Database Adapter
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success


                        if (!register())
                            onSignupFailed();
                        else
                            onSignupSuccess();

                        progressDialog.dismiss();

                    }
                }, 2000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    public boolean register() {
        boolean register = true;



        String name = _nameText.getText().toString();
        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        IRepositoryUser repositoryUser = new userRepo(getApplicationContext());

        ArrayList<User> test = repositoryUser.GetAll();
        String size = String.valueOf(test.size());
        Log.d("Size test",  size);

        User user = new User();
        user.setName(name.toString());
        user.setEmail(username.toString());
        user.setPassword(password.toString());
        user.setId(test.size() + 1);
        repositoryUser.Save(user);

        Log.d("New Size test", size);
        //dbAdapter.registerUser(name,username,password);
        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

        return register;

    }

    @Override

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        dbAdapter.close();
    }
}

 /*@Override
            public void onClick(View arg0) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(txtPassword.getWindowToken(), 0);
                try {

                    String username = txtUserName.getText().toString();
                    String password = txtPassword.getText().toString();
                    long i = dbAdapter.register(username, password);
                    if(i != -1)
                        Toast.makeText(login.this, "You have successfully registered",Toast.LENGTH_LONG).show();

                } catch (SQLException e) {
                    Toast.makeText(login.this, "Some problem occurred",
                            Toast.LENGTH_LONG).show();

                }

            }
            */


