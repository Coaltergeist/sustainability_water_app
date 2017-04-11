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

        if (!nonEmptyStrings(username, password, passwordConfirm, email)) {
            new AlertDialog.Builder(this)
                    .setTitle("Empty fields")
                    .setMessage("Please fill out every field")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
        }

        try {
            passwordCheck(password, passwordConfirm);
        } catch (IllegalArgumentException e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(e.getMessage())
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

    /**
     * Check to make sure all Strings are nonempty
     * @param strings Strings to check
     * @return True if all strings aren't empty, false if there is at least one empty
     */
    private boolean nonEmptyStrings(String... strings) {
        for (String s : strings) {
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Perform checks to determine password consistency and strength
     * @throws IllegalArgumentException Password check fails
     * @param password Password from password field
     * @param confirm Password confirmation from confirmation field
     * @return True if pass
     */
    private boolean passwordCheck(String password, String confirm) throws IllegalArgumentException {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password needs to be at least 6 characters");
        }
        if (!password.equals(confirm)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        return true;
    }

    /**
     * Show successful popup
     */
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

    /**
     * Show failure popup
     */
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
