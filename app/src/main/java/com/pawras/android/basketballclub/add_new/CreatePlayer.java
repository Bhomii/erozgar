package com.pawras.android.basketballclub.add_new;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.models.CoachAndPlayer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePlayer extends AppCompatActivity implements View.OnClickListener {

    EditText mFirstName, mLastName, mDateOfBirth, mEmail, mAddress, mContactNumber;
    String firstName, lasName, dateOfBirth, email, address, contactNumber;
    Button createCoach;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);
        initialization();
    }

    public void initialization() {
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.sure_name);
        mDateOfBirth = (EditText) findViewById(R.id.date_of_birth);
        mEmail = (EditText) findViewById(R.id.email);
        mAddress = (EditText) findViewById(R.id.address);
        mContactNumber = (EditText) findViewById(R.id.contact_number);
        createCoach = (Button) findViewById(R.id.create_coach);
        createCoach.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        firstName = mFirstName.getText().toString();
        lasName = mLastName.getText().toString();
        dateOfBirth = mDateOfBirth.getText().toString();
        switch (v.getId()) {
            case R.id.create_coach:
                if (validate()) {
                    if (haveNetworkConnection()) {
                        loading = ProgressDialog.show(CreatePlayer.this, "Creating Coach", "Please wait", false, false);
                        addPlayerToFirebaseDatabase();
                    } else {
                        openDialog();
                    }
                }
        }
    }

    //validate the fields
    public boolean validate() {
        firstName = mFirstName.getText().toString();
        lasName = mLastName.getText().toString();
        dateOfBirth = mDateOfBirth.getText().toString();
        email = mEmail.getText().toString();
        contactNumber = mContactNumber.getText().toString();
        address = mAddress.getText().toString();
        if (firstName.equals("") || firstName.equals(null)) {
            mFirstName.requestFocus();
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (lasName.equals("") || lasName.equals(null)) {
            mLastName.requestFocus();
            Toast.makeText(this, "Enter Sure Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isEmailValid(email)) {
            mEmail.requestFocus();
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dateOfBirth.equals("") || dateOfBirth.equals(null)) {
            mDateOfBirth.requestFocus();
            Toast.makeText(this, "Enter date of birth", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.equals("") || email.equals(null)) {
            mEmail.requestFocus();
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (contactNumber.equals("") || contactNumber.equals(null)) {
            mEmail.requestFocus();
            Toast.makeText(this, "Enter contact Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (address.equals("") || address.equals(null)) {
            mAddress.requestFocus();
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    // adding email to firebase database
    public void addPlayerToFirebaseDatabase() {
        //perform auto increment
        SharedPreferences mSharedPreferences = getSharedPreferences("identity", MODE_PRIVATE);
        String id = mSharedPreferences.getString("p_id", "0");
        int idd = Integer.parseInt(id);
        idd++;
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("p_id", Integer.toString(idd));
        mEditor.commit();

        //write to the database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("player");
        String userId = mFirebaseDatabase.push().getKey();
        CoachAndPlayer instance = new CoachAndPlayer(address, dateOfBirth, contactNumber, email, lasName, firstName, Integer.toString(idd));
        mFirebaseDatabase.child(userId).setValue(instance);
        loading.dismiss();
        Toast.makeText(this, "Player Created", Toast.LENGTH_SHORT).show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("coaches").orderByChild("contact_number").equalTo("0313731743");

        //delete sub node
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        finish();
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void openDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_connection, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog findMeDialog = dialogBuilder.create();
        findMeDialog.show();
        LinearLayout reset_btn = (LinearLayout) dialogView.findViewById(R.id.ok);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findMeDialog.dismiss();
            }
        });
    }

}