package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void signOut(View v) {
        mAuth.signOut();
        Log.d("SIGN OUT", "SUCCESS");
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();

    }

    public void seeVehicles(View v) {
        if (mUser != null){
            Intent intent = new Intent(this, VehiclesInfoActivity.class);
            startActivity(intent);
            finish();
        }

    }

}