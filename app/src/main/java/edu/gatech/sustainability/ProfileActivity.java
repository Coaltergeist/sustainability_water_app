package edu.gatech.sustainability;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.sustainability.model.user.UserType;

public class ProfileActivity extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((EditText) findViewById(R.id.editEmailText)).setText(MainActivity.currentUser.getEmail());
        ((EditText) findViewById(R.id.editAddressText)).setText(MainActivity.currentUser.getHomeAddress());
        ((EditText) findViewById(R.id.phoneNumberText)).setText(MainActivity.currentUser.getPhoneNumber());
        spinner = (Spinner) findViewById(R.id.profileRoleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        switch (MainActivity.currentUser.getUserType()) {
            case NORMALUSER:
                spinner.setSelection(0);
                break;
            case WORKER:
                spinner.setSelection(1);
                break;
            case MANAGER:
                spinner.setSelection(2);
                break;
            case ADMINISTRATOR:
                spinner.setSelection(3);
                break;
        }
    }

    /**
     * Save profile to user object
     * @param view Originating view
     */
    public void saveProfile(View view) {
        if (((EditText) findViewById(R.id.editEmailText)).getText().toString().isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid email")
                    .setMessage("Please retype your email")
                    .setPositiveButton("Back", (dialogInterface, i) -> { })
                    .show();
        }
        switch((String) spinner.getSelectedItem()) {
            case "User":
                MainActivity.currentUser.setUserType(UserType.NORMALUSER);
                break;
            case "Worker":
                MainActivity.currentUser.setUserType(UserType.WORKER);
                break;
            case "Manager":
                MainActivity.currentUser.setUserType(UserType.MANAGER);
                break;
            case "Administrator":
                MainActivity.currentUser.setUserType(UserType.ADMINISTRATOR);
                break;
            default:
                MainActivity.currentUser.setUserType(UserType.NORMALUSER);
                break;
        }
        MainActivity.currentUser.setEmail(((EditText) findViewById(R.id.editEmailText)).getText().toString());
        MainActivity.currentUser.setHomeAddress(((EditText) findViewById(R.id.editAddressText)).getText().toString());
        MainActivity.currentUser.setPhoneNumber(((EditText) findViewById(R.id.phoneNumberText)).getText().toString());
        MainActivity.userDatabase.child(MainActivity.currentUser.getUserId())
                .setValue(MainActivity.currentUser);
        backToMain(view);
    }

    /**
     * Go back to main screen
     * @param view Originating view
     */
    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
