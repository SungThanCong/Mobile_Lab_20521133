package com.example.mobile_lab_20521133;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobile_lab_20521133.Adapter.PersonItemAdapter;
import com.example.mobile_lab_20521133.Model.Person;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtHoTen, edtLuong;
    Button btnThem;
    ListView lsvResult;

    ArrayList<Person> data = new ArrayList<>();
    PersonItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddControl();
        AddEvent();
    }

    private void AddControl() {
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtLuong = (EditText) findViewById(R.id.edtLuong);
        btnThem = (Button) findViewById(R.id.btnThem);
        lsvResult = (ListView) findViewById(R.id.lsvKetQua);

        adapter = new PersonItemAdapter(this,data);
        lsvResult.setAdapter(adapter);
    }

    private void AddEvent(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckValidate()){
                    String hoTen = edtHoTen.getText().toString();
                    double luong = Double.parseDouble(edtLuong.getText().toString());

                    Person newPerson = new Person(hoTen,LuongNetCaculation(luong));
                    data.add(newPerson);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
                    edtLuong.setText("");
                    edtHoTen.setText("");
                }

            }
        });
    }

    private boolean CheckValidate(){
        if (edtHoTen.getText().length() == 0){
            Toast.makeText(this,"Trường họ tên không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtLuong.getText().length() <= 0 || TextUtils.isDigitsOnly(edtLuong.getText().toString()) == false){
            Toast.makeText(this,"Trường lương gross không được để trống và phải nhập số", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private double LuongNetCaculation(double luong){
        double a =  luong - luong*0.1;
        if (a >= 11000000){
            return 11000000 + (a - 11000000) * (1 - 0.15);
        }
        return a;
    }
}