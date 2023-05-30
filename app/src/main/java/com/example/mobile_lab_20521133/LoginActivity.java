package com.example.mobile_lab_20521133;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_lab_20521133.Common.Encrypt;
import com.example.mobile_lab_20521133.Common.Validate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    TextView txtLinkSignUp;

    FirebaseFirestore firestore;
    SharedPreferences ref;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences  = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        firestore = FirebaseFirestore.getInstance();
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtLinkSignUp = findViewById(R.id.txtLinkSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(Validate.ValidateUsername(username) == false){
                    Toast.makeText(getApplicationContext(),"Username must contains only alphabets and minimum 6 characters",Toast.LENGTH_LONG).show();
                    return;
                }
                if(Validate.ValidatePassword(password) == false){
                    Toast.makeText(getApplicationContext(),"Password must contains minimum 6 characters",Toast.LENGTH_LONG).show();
                    return;
                }
                firestore.collection("account")
                    .whereEqualTo("username",edtUsername.getText().toString())
                    .whereEqualTo("password", Encrypt.HashPasswordMd5(edtPassword.getText().toString()))
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(!task.getResult().isEmpty()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.apply();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                            }
                            
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();

                        }
                    });
            }
        });
        txtLinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });


    }
}
