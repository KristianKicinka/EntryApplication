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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class departureActivity extends AppCompatActivity {

    private AutoCompleteTextView place;
    private Button sendit;
    private User user;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_departure);


        place = findViewById(R.id.placeField);
        sendit = findViewById(R.id.arrival);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference= database.getReference("Places");
        readPlaces();


        reference= FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.currentUserKey);
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

    public void readPlaces(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> placesNames = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    placesNames.add(ds.getValue(Place.class).getPlace_name());
                }
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,placesNames);
                place.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Dáta sa nepodarilo načítať",Toast.LENGTH_LONG).show();
            }
        });
    }
}
