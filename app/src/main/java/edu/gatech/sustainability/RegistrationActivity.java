package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Map;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.User;
import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.UserType;

public class RegistrationActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        spinner = (Spinner) findViewById(R.id.registrationRoleSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.user_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Registration method
     * @param view Originating view
     */
    public void register(View view) {
        String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String password = ((EditText) findViewById(R.id.registerPasswordText)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
        String passwordConfirm = ((EditText) findViewById(R.id.confirmPasswordText)).getText().toString();

        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || email.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Empty fields")
                    .setMessage("Please fill out every field")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
        }
        for (Map.Entry<Integer, User> entry : MainActivity.userSet.entrySet()) {
            User u = entry.getValue();
            if (u.getUsername().toLowerCase().equals(username.toLowerCase())) {
                new AlertDialog.Builder(this)
                        .setTitle("Username unavailable")
                        .setMessage("This username is already taken")
                        .setPositiveButton("Back", (dialogInterface, i) -> { })
                        .show();
                return;
            } else if (u.getEmail().toLowerCase().equals(email.toLowerCase())) {
                new AlertDialog.Builder(this)
                        .setTitle("Email unavailable")
                        .setMessage("This email is already taken")
                        .setPositiveButton("Back", (dialogInterface, i) -> { })
                        .show();
                return;
            }
        }
        if (password.length() < 4) {
            new AlertDialog.Builder(this)
                    .setTitle("Password too short")
                    .setMessage("Passwords must be at least 4 characters long")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
            return;
        }
        if (!password.equals(passwordConfirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage("Please type in your password again")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
            return;
        }
        User user = new User();
        switch((String) spinner.getSelectedItem()) {
            case "User":
                user.setType(UserType.NORMALUSER);
                break;
            case "Worker":
                user.setType(UserType.WORKER);
                break;
            case "Manager":
                user.setType(UserType.MANAGER);
                break;
            case "Administrator":
                user.setType(UserType.ADMINISTRATOR);
                break;
            default:
                user.setType(UserType.NORMALUSER);
                break;
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        MainActivity.userSet.put(user.getUserId(), user);
        new AlertDialog.Builder(this)
                .setTitle("Successfully registered")
                .setMessage("Login with your new credentials")
                .setPositiveButton("Login", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                })
                .show();
    }

    /**
     * When the user taps the cancel button, go back to login view
     * @param view Originating view
     */
    public void cancelToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
