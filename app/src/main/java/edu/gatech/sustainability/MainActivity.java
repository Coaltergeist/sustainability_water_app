package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user.User;

public class MainActivity extends AppCompatActivity {
    public static Map<Integer, User> userSet = new HashMap<>();
    public static User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (currentUser == null)
            currentUser = userSet.get(intent.getIntExtra("userId", -1));
        ((TextView) findViewById(R.id.welcomeTextView)).setText("Welcome, " + currentUser.getUsername());
    }

    /**
     * Button action for logging out. Clear user and return to splash/login screen
     * @param view View this was done from
     */
    public void logout(View view) {
        currentUser = null;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
