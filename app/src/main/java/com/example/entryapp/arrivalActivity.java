package com.example.entryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class arrivalActivity extends AppCompatActivity {

    private Button arrival;
    private User user;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_arrival);

        arrival = findViewById(R.id.arrival);
        reference= FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.currentUserKey);


        final Date date = Calendar.getInstance().getTime();

        final String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date);

        arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("arrival").setValue(currentDateTime);
                reference.child("here").setValue("ishere");
                reference.child("place").setValue("Internat");
                Toast.makeText(getApplicationContext(),"Dáta sa uložili",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });





    }
}
