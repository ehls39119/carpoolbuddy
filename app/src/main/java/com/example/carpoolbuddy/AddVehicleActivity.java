package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddy.classDictionaries.Car;
import com.example.carpoolbuddy.classDictionaries.ElectricCar;
import com.example.carpoolbuddy.classDictionaries.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class AddVehicleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseFirestore db;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    private Spinner sVehicleType;
    private EditText originLocation;
    private EditText carModel;
    private EditText capacityAvailable;
    private EditText vehicleRating;
    private EditText vehicleOwner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        originLocation = findViewById(R.id.originLocationID);
        carModel = findViewById(R.id.vehicleModelID);
        vehicleRating = findViewById(R.id.vehicleCapacityID);
        capacityAvailable = findViewById(R.id.ratingID);
        vehicleOwner = findViewById(R.id.vehicleOwnerID);


        //////spinner
        sVehicleType = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.vehicles, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sVehicleType.setAdapter(adapter2);
        sVehicleType.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ///spinner
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean formValid(String str1, String str2, int str3, String str4, String str5, String str6) {
        return str1 != null && str2 != null && str3  > 0 && str4 != null && str5 != null && str6!=null;
    }

    public void addNewVehicle(View v) {
        String ownerString = vehicleOwner.getText().toString();
        String locationString = originLocation.getText().toString();
        String modelString = carModel.getText().toString();
        String txtSpinner2 = sVehicleType.getSelectedItem().toString();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        int rating = Integer.parseInt((vehicleRating.getText().toString()));
        int fakeCapacity = Integer.parseInt((capacityAvailable).getText().toString());
        ArrayList<String> creator = new ArrayList<>();
        creator.add(userID);


//        if ((formValid(locationString, modelString, rating, ratingString, ownerString, vehicleType))) {
        if (txtSpinner2.equals("Car")) {
            txtSpinner2 = "Car";
            Vehicle newCar = new Car(locationString, modelString, creator, rating, fakeCapacity, ownerString, txtSpinner2);
            db.collection("Vehicle").document(ownerString).set(newCar);

//            db.collection("Vehicle").add(newCar).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(AddVehicleActivity.this, "NEW CAR ADDED", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(AddVehicleActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
        }

        if (txtSpinner2.equals("ElectricCar")) {
            txtSpinner2 = "ElectricCar";
            Vehicle newElectric = new ElectricCar(locationString, modelString, creator, rating, fakeCapacity, ownerString, txtSpinner2);
            db.collection("Vehicle").document(ownerString).set(newElectric);
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(AddVehicleActivity.this, "NEW ELECTRIC", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(AddVehicleActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }

        }

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);

    }
}







