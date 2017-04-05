package edu.gatech.sustainability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

import edu.gatech.sustainability.model.user.User;
import edu.gatech.sustainability.model.user.UserType;

public class RegistrationActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    System.out.println("Logged in");
                } else {
                   System.out.println("Logged out");
                }
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        spinner = (Spinner) findViewById(R.id.registrationRoleSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.user_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
        if (password.length() < 6) {
            new AlertDialog.Builder(this)
                    .setTitle("Password too short")
                    .setMessage("Passwords must be at least 6 characters long")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
            return;
        }
        if (!password.equals(passwordConfirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage("Please userType in your password again")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
            return;
        }
        User user = new User();
        switch((String) spinner.getSelectedItem()) {
            case "User":
                user.setUserType(UserType.NORMALUSER);
                break;
            case "Worker":
                user.setUserType(UserType.WORKER);
                break;
            case "Manager":
                user.setUserType(UserType.MANAGER);
                break;
            case "Administrator":
                user.setUserType(UserType.ADMINISTRATOR);
                break;
            default:
                user.setUserType(UserType.NORMALUSER);
                break;
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        //MainActivity.userSet.put(user.getUserId(), user);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            showFailedRegistration();
                            System.out.println(task.getException());
                        } else {
                            showSuccessfulRegistration();
                            user.setUserId(task.getResult().getUser().getUid());
                            MainActivity.userDatabase.child(task.getResult().getUser().getUid())
                                    .setValue(user);
                        }

                    }
                });
        /*MainActivity.firebase.createUser("bobtony@firebase.com", "correcthorsebatterystaple",
                new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                showSuccessfulRegistration();
            }
            @Override
            public void onError(FirebaseError err) {
                System.out.println(err);
                showFailedRegistration();
            }
        });*/

    }
    private void showSuccessfulRegistration() {
        new AlertDialog.Builder(this)
                .setTitle("Successfully registered")
                .setMessage("Login with your new credentials")
                .setPositiveButton("Login", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                })
                .show();
    }
    private void showFailedRegistration() {
        new AlertDialog.Builder(this)
                .setTitle("Failed to register")
                .setMessage("Please try again")
                .setPositiveButton("Back", (dialogInterface, i) -> {

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
