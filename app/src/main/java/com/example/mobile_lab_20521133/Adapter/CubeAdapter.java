package com.example.mobile_lab_20521133.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.mobile_lab_20521133.R;

public class CubeAdapter extends BaseAdapter {
    Context context;
    String Colors[];
    LayoutInflater layoutInflater;

    public CubeAdapter(Context context, String[] colors) {
        super();
        this.context = context;
        this.Colors = colors;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return Colors.length;
    }

    @Override
    public Object getItem(int i) {
        return Colors[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.cube_item_view, null);
        FrameLayout cube = (FrameLayout) view.findViewById(R.id.main_block);
        cube.setBackgroundColor(Color.parseColor(Colors[i]));
        return view;
    }
}
