package com.example.mobile_lab_20521133;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobile_lab_20521133.Model.DBHelper;
import com.example.mobile_lab_20521133.Model.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtPhone;
    private Button btnAddSqlite, btnAddFs, btnQuerySqlite, btnQueryFs;
    private ListView lsvData;
    private ArrayList<String> personData;
    private ArrayAdapter<String> personAdapter;
    private FirebaseFirestore firestore;
    private DBHelper sqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();
        personData = new ArrayList<>();
        sqlite = new DBHelper(this);

        AddControls();
        AddEvents();
    }
    private void AddControls() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnAddSqlite = findViewById(R.id.btnAddSqlite);
        btnAddFs = findViewById(R.id.btnAddFs);
        btnQueryFs = findViewById(R.id.btnQueryFs);
        btnQuerySqlite = findViewById(R.id.btnQuerySqlite);
        lsvData = findViewById(R.id.lsvData);

        personAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,personData);
        lsvData.setAdapter(personAdapter);
    }

    private void AddEvents() {
        btnAddFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();

                if(name.length() <= 0 && phone.length() <= 0) {
                    Toast.makeText(getApplicationContext(),"Input không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create a new user with a first and last name
                Map<String, Object> value = new HashMap<>();
                value.put("name", name);
                value.put("phone",phone);

                // Add a new document with a generated ID
                firestore.collection("person")
                        .add(value)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(),"Thêm dữ liệu thành công",Toast.LENGTH_LONG).show();
                                edtName.setText("");
                                edtPhone.setText("");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(getApplicationContext(),"Thêm dữ liệu thất bại",Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });

        btnQueryFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personData.clear();
                firestore.collection("person")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    personData.add("Name: " +document.get("name").toString() + "\nPhone: "+ document.get("phone").toString());
                                }
                                personAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Lấy dữ liệu thành công", Toast.LENGTH_LONG).show();

                            } else {
                                Log.w(TAG, "Error query document");
                                Toast.makeText(getApplicationContext(), "Lấy dữ liệu thất bại", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });

        btnAddSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();

                if(name.length() <= 0 && phone.length() <= 0) {
                    Toast.makeText(getApplicationContext(),"Input không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(sqlite.addNewPerson(name,phone)){
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtPhone.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Thêm không thành công",Toast.LENGTH_SHORT).show();

                };

            }
        });

        btnQuerySqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personData.clear();

                for (Person item : sqlite.showPerson())
                {
                    personData.add("Name: " +item.getFullName() + "\nPhone: "+ item.getPhoneNumber());
                };
                Toast.makeText(getApplicationContext(), "Lấy dữ liệu thành công", Toast.LENGTH_LONG).show();
                personAdapter.notifyDataSetChanged();

            }
        });

    }

    
}