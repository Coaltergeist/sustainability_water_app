package edu.gatech.sustainability;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    static String username = "user";
    static String password = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.loginButton);

    }

    /**
     * Login method to check for username and passwords
     * @param view View this was done from
     */
    public void login(View view) {
        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        if (usernameText.getText().toString().equals(username)
                && passwordText.getText().toString().equals(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", usernameText.getText().toString());
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid login credentials")
                    .setMessage("Your login details are incorrect")
                    .setPositiveButton("Retry", (dialogInterface, i) -> {
                        // Backout of confirmation box, increment a counter to lockout users
                    })
                    .setNegativeButton("Register", (dialogInterface, i) -> {
                        // Registration stuff
                    })
                    .show();
        }
    }
}
