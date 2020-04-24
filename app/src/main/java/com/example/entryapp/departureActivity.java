package com.example.entryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class departureActivity extends AppCompatActivity {

    private AutoCompleteTextView place;
    private Button sendit;
    private User user;
    private DatabaseReference reference;

    private static final String[] PLACES = new String[]{
      "Tesco","Škola","Krúžok","Eperia","Max","Mesto","Posilňovňa","Ine"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_departure);


        place = findViewById(R.id.placeField);
        sendit = findViewById(R.id.arrival);

        user = new User();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.currentUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, PLACES);

        place.setAdapter(adapter);


        final Date date = Calendar.getInstance().getTime();

        final String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date);

        sendit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("departure").setValue(currentDateTime);
                reference.child("here").setValue("isnothere");
                reference.child("place").setValue(place.getText().toString());
                Toast.makeText(getApplicationContext(),"Dáta sa uložili",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
