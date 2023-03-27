package com.example.mobile_lab_20521133;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_lab_20521133.Adapter.CubeAdapter;
import com.example.mobile_lab_20521133.Adapter.GridAdapter;
import com.example.mobile_lab_20521133.Model.Block;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lsvTest;
    private GridView grvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lsvTest = (ListView) findViewById(R.id.lsvTest);
        this.grvTest = (GridView) findViewById(R.id.grvTest);


        SeedData();
    }
    private void SeedData(){
        String colors[] = {"#FF0000","#FFFF00","#8BC34A"};
        CubeAdapter adapter = new CubeAdapter(getApplicationContext(), colors);

        lsvTest.setAdapter(adapter);

        ArrayList<Block>blocks = new ArrayList<Block>();
        blocks.add(new Block("Block 1","#FF0000"));
        blocks.add(new Block("Block 2","#FFFF00"));
        blocks.add(new Block("Block 3","#8BC34A"));
        blocks.add(new Block("Block 4","#FF0000"));

        GridAdapter adapter2 = new GridAdapter(getApplicationContext(), blocks);
        grvTest.setAdapter(adapter2);



    }
}