package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpoolbuddy.classDictionaries.Vehicle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VehicleProfileActivity extends AppCompatActivity{
    TextView owner;
    TextView location;
    TextView model;
    TextView capacity;
    TextView type;
    TextView passengers;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    Button bn;
//    Vehicle vehicleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        owner = findViewById(R.id.userView);
        location = findViewById(R.id.locationView);
        model = findViewById(R.id.modelView);
        capacity = findViewById(R.id.capacityView);
//        type = findViewById(R.id.carTypeView);
        passengers = findViewById(R.id.passengersView);


        owner.setText(getIntent().getStringExtra("ownerName"));
        location.setText(getIntent().getStringExtra("location"));
        model.setText(getIntent().getStringExtra("model"));
        capacity.setText(getIntent().getStringExtra("capacity"));
//        type.setText(getIntent().getStringExtra("type"));
        passengers.setText(getIntent().getStringExtra("passengers"));
        bn = (Button)findViewById(R.id.bookRideID);






    }

    public void close(View v){
        String ownerName = getIntent().getStringExtra("ownerName");
//        System.out.println("owner name " + ownerName);
//        System.out.println("curr name " + mUser.getEmail());


        if (mUser.getEmail().contains(ownerName)){

            System.out.println("reached");
            bn.setVisibility(View.INVISIBLE);
//            bn.setVisibility(v.INVISIBLE);


        }
        else{
            System.out.println("close broken");
        }
    }

    public void open(View v){
        String ownerName = getIntent().getStringExtra("ownerName");
        if ((bn.getVisibility() == View.INVISIBLE) && (mUser.getEmail().contains(ownerName))){
            bn.setVisibility(View.VISIBLE);
        }
        else{
            System.out.println("open broken");
        }
    }



    public void bookRide(View v){
        Context context = v.getContext();
        String ownerName = getIntent().getStringExtra("ownerName");
        String newPassenger = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String location = getIntent().getStringExtra("location");
        String model = getIntent().getStringExtra("model");
        String type = getIntent().getStringExtra("type");
        int totalPassengersInt = Integer.parseInt(capacity.getText().toString())- 1;


        String currPass = getIntent().getStringExtra("passengers");
        ArrayList<String> passengers = new ArrayList<>();
        String[] result = currPass.split("\n");

        for (String x: result){
            passengers.add(x);
        }

        passengers.add(newPassenger);

//        String r = currPass + ", " + newPassenger;

        Vehicle newV = new Vehicle(location,model, passengers, 0, totalPassengersInt, ownerName, type);

        if (totalPassengersInt > -1) {
            //Decreases the Total available seats in database
//            db.collection("Vehicle").document(ownerName).update("rating", totalPassengersInt, "riderIds", currentPassengers);
            db.collection("Vehicle").document(ownerName).set(newV);
//            db.collection("Vehicle").document(ownerName).update("riderIDS", r);
            Toast.makeText(v.getContext(), "Successfully Registred for Ride", Toast.LENGTH_SHORT).show();

            //Restarts the intent to clear all the values after booking seat
            Intent intent = new Intent(context, VehiclesInfoActivity.class);
            context.startActivity(intent);
        } else {

            //Shows this toast if total seats are booked in vehicle
            Toast.makeText(context, "Please,Choose another Ride, IT IS FULL", Toast.LENGTH_SHORT).show();
        }

    }
}