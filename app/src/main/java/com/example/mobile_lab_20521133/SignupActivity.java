package com.example.mobile_lab_20521133;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_lab_20521133.Common.Encrypt;
import com.example.mobile_lab_20521133.Common.Validate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button btnSignup;
    EditText edtUsername, edtPassword,edtPhone, edtName;
    TextView txtLinkSignUp;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firestore = FirebaseFirestore.getInstance();
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        edtPhone = findViewById(R.id.edtPhone);
        btnSignup = findViewById(R.id.btnSignup);
        txtLinkSignUp = findViewById(R.id.txtLinkLogin);
        txtLinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
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
                String phone = edtPhone.getText().toString();
                String name = edtName.getText().toString();

                Map<String, Object> value = new HashMap<>();
                value.put("name", name);
                value.put("phone",phone);
                value.put("username",username);
                value.put("password", Encrypt.HashPasswordMd5(password));

                firestore.collection("account").add(value)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(),"Sign up success",Toast.LENGTH_LONG).show();
                                edtName.setText("");
                                edtPhone.setText("");
                                edtPassword.setText("");
                                edtUsername.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(getApplicationContext(),"Sign up failed",Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });

    }


}