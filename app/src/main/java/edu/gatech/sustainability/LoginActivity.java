package edu.gatech.sustainability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.sustainability.model.user.User;
import edu.gatech.sustainability.model.user.UserType;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    System.out.println("Signed in");

                } else {
                    System.out.println("Signed out");
                }
            }
        };

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
     * Login method to check for username and passwords
     *
     * @param view View this was done from
     */
    public void login(View view) {
        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);

        mAuth.signInWithEmailAndPassword(usernameText.getText().toString(), passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (!task.isSuccessful()) {
                            badLogin();
                            return;
                        }
                        System.out.println("Successful");
                        String userId = task.getResult().getUser().getUid();
                        String username;
                        UserType type;
                        MainActivity.userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                                    // String uid = snap.getKey();
                                    if (snap.getKey().equals(userId)) {
                                        Log.e("key", snap.getKey());
                                        String name = (String) snap.child("name").getValue();
                                        String type = (String) snap.child("userType").getValue();
                                        String address = (String) snap.child("address").getValue();
                                        String phone = (String) snap.child("phoneNumber").getValue();
                                        MainActivity.currentUser = new User(userId, name,
                                                task.getResult().getUser().getEmail(), null, UserType.fromString(type));
                                        MainActivity.currentUser.setHomeAddress(address);
                                        MainActivity.currentUser.setPhoneNumber(phone);
                                        login();
                                        Log.e("login", name + " | " + type);
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                System.out.println(error);
                            }
                        });

                    }
                });
    }


    /**
     * Finish login & go to main screen
     */
    private void login() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("username", entry.getKey());
        intent.putExtra("reportNum", 0);
        startActivity(intent);
        return;
    }

    /**
     * Alert the user to a bad login attempt
     */
    private void badLogin() {
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
