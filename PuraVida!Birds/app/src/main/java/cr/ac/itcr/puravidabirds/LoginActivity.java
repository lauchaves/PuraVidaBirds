package cr.ac.itcr.puravidabirds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cr.ac.itcr.puravidabirds.access_data.DBAdapter;


public class LoginActivity extends AppCompatActivity {
    DBAdapter dbAdapter;


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (!validateLogin())
                            onLoginFailed();
                        else {

                            onLoginSuccess();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        _emailText.setText("");
        _passwordText.setText("");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

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

    //sqlite funcion
    public boolean validateLogin(){
        boolean valid = true;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(_emailText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(_passwordText.getWindowToken(), 0);
        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        if (username.length() > 0 && password.length() > 0) {
            try {

                if (dbAdapter.Login(username, password)) {
                    Toast.makeText(LoginActivity.this,
                            "Successfully Logged In", Toast.LENGTH_LONG)
                            .show();
                    dbAdapter.getUserName(username);



                } else {
                    Toast.makeText(LoginActivity.this,
                            "Invalid username or password",
                            Toast.LENGTH_LONG).show();
                    valid = false;
                }

            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Some problem occurred",
                        Toast.LENGTH_LONG).show();
                valid = false;

            }
        } else {
            Toast.makeText(LoginActivity.this,
                    "Username or Password is empty", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;

    }

}
