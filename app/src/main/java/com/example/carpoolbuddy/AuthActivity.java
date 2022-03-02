package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.ApiException;
import com.example.carpoolbuddy.classDictionaries.Alumni;
import com.example.carpoolbuddy.classDictionaries.Parent;
import com.example.carpoolbuddy.classDictionaries.Student;
import com.example.carpoolbuddy.classDictionaries.Teacher;
import com.example.carpoolbuddy.classDictionaries.User;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AuthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //properties

//    private GoogleSignIn mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String selected;
    private Spinner sUserType;

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
//    private GoogleSignInClient mGoogleSignInClient;
//    private final static int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        nameField = findViewById(R.id.nameID);
        emailField = findViewById(R.id.editTextEmailID);
        passwordField = findViewById(R.id.editTextPasswordID);


        //////spinner
        sUserType = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sUserType.setAdapter(adapter);
        sUserType.setOnItemSelectedListener(this);
        ///spinner

    }




    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }


    public void signIn(View v) {

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("SIGN IN", "LOGIN SUCCESS");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SIGN IN", "LOGIN FAIL", task.getException());
                    updateUI(null);
                }
            }
        });
    }

    public void switchSignUp(View v){
        Intent intent = new Intent(this, SignUpAScreen.class);
        startActivity(intent);


    }

    public void signUp(View v) {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("SIGN UP", "SIGNUP SUCCESS");
                    String txtFromSpinner1 = sUserType.getSelectedItem().toString();

                    if (txtFromSpinner1.equals("Student")){
                        Student newStudent = new Student(name, email);
                        db.collection("Users").document("Students").collection("Student").document(newStudent.getDisplayName()).set(newStudent);
                    }

                    if (txtFromSpinner1.equals("Teacher")){
                        Teacher newTeacher = new Teacher(name, email);
                        db.collection("Users").document("Teachers").collection("Teacher").document(newTeacher.getDisplayName()).set(newTeacher);
                    }

                    if (txtFromSpinner1.equals("Parent")){
                        Parent newParent = new Parent(name, email);
                        db.collection("Users").document("Parents").collection("Parent").document(newParent.getDisplayName()).set(newParent);
                    }

                    if (txtFromSpinner1.equals("Alumni")){
                        Alumni newAlumni = new Alumni(name, email);
                        db.collection("Users").document("Alumni").collection("Alumni").document(newAlumni.getDisplayName()).set(newAlumni);
                    }

                    System.out.println("name field"+ name);
                    System.out.println("spinner shit"+ txtFromSpinner1);
//                    System.out.println("display check " + user.getDisplayName()); //currently reutrn s null
                    updateUI(null);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SIGN UP", "SIGNUP FAIL", task.getException());
                    updateUI(null);
                }
            }
        });
    }

    void updateUI(FirebaseUser user){
        if (user != null){
            System.out.println("user exists and is logged in");
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



}