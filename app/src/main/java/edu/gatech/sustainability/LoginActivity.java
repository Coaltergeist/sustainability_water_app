package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Map;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.User;

public class LoginActivity extends AppCompatActivity {

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
        for (Map.Entry<Integer, User> entry : MainActivity.userSet.entrySet()) {
            String username = entry.getValue().getUsername();
            String password = entry.getValue().getPassword();
            if (usernameText.getText().toString().equalsIgnoreCase(username)
                    && passwordText.getText().toString().equalsIgnoreCase(password)) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("userId", entry.getKey());
                startActivity(intent);
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Invalid login credentials")
                .setMessage("Your login details are incorrect")
                .setPositiveButton("Retry", (dialogInterface, i) -> {
                    // Backout of confirmation box, increment a counter to lockout users
                })
                .setNegativeButton("Register", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, RegistrationActivity.class);
                    startActivity(intent);
                })
                .show();
    }

    /**
     * Send the user to register
     * @param view Originating view
     */
    public void toRegister(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
