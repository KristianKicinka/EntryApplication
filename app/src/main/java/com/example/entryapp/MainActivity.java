package com.example.entryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private AutoCompleteTextView userName;
    private Button submit;
    public static String currentUser;
    public static String currentUserKey;
    private DatabaseReference reference;
    private DataSnapshot snapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        userName = findViewById(R.id.placeField);
        submit = findViewById(R.id.arrival);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference= database.getReference("Users");
        readUsers();






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentUser = userName.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                reference= database.getReference("Users");
                readFromDB();




            }
        });
    }

    public void readFromDB(){

        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> Users = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Users.add(ds.getValue(User.class));
                }
                for (User user:Users) {
                    if (user.getName().equals(currentUser)){

                        try {
                            user.setName(user.getName());
                            user.setArrival(user.getArrival());
                            user.setDeparture(user.getDeparture());
                            user.setHere(user.getHere());
                            user.setPlace(user.getPlace());
                            currentUserKey = user.getKey();

                            if(user.getHere().equals("ishere")){
                                Intent intent = new Intent(getApplicationContext(),departureActivity.class);
                                startActivity(intent);
                            }else if(user.getHere().equals("isnothere")){
                                Intent intent = new Intent(getApplicationContext(),arrivalActivity.class);
                                startActivity(intent);
                            }


                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Dáta sa nepodarilo načítať",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void readUsers(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userNames = new ArrayList<>();
                User user = new User();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    userNames.add(ds.getValue(User.class).getName());
                }
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,userNames);
                userName.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Dáta sa nepodarilo načítať",Toast.LENGTH_LONG).show();
            }
        });
    }




}
