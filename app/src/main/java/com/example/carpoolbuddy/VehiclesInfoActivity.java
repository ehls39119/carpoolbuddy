package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carpoolbuddy.classDictionaries.Car;
import com.example.carpoolbuddy.classDictionaries.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VehiclesInfoActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

//    Vehicle vehicleInfo;

    RecyclerView recView;
//    ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
    ArrayList<Vehicle> vehiclesList = new ArrayList<>();
//    public VehicleRecyclerViewAdapter.OnNoteListener mOnNoteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler1ID);
        VehicleRecyclerViewAdapter myAdapter = new VehicleRecyclerViewAdapter(vehiclesList, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getAndPopulateData(View v) {


        db.collection("Vehicle").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                            List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
                            for (DocumentSnapshot value : documents) {
                                Vehicle newV = value.toObject(Vehicle.class);
                                vehiclesList.add(newV);
                            }

                            VehicleRecyclerViewAdapter recAdapter = (VehicleRecyclerViewAdapter) recView.getAdapter();
                            assert recAdapter != null;
                            recAdapter.setVehiclesData(vehiclesList);
                            recAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }


    public void goToAddVehicle(View v){
        if (mUser != null){
            Intent intent = new Intent(this, AddVehicleActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }
    }


}