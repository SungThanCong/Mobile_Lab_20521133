package com.example.mobile_lab_20521133.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobile_lab_20521133.Model.Person;
import com.example.mobile_lab_20521133.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PersonItemAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater inflater;
    ArrayList<Person> data;

    public PersonItemAdapter(Context context, ArrayList<Person> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.person_item,null);
        TextView txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);
        TextView txtLuongNet = (TextView) view.findViewById(R.id.txtLuongNet);
        DecimalFormat formatter  = new DecimalFormat("#,###");

        txtHoTen.setText(data.get(i).getHoTen());
        txtLuongNet.setText(formatter.format(data.get(i).getLuong()) + "");

        return view;
    }
}
