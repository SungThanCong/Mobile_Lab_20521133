package com.example.mobile_lab_20521133.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mobile_lab_20521133.Model.Block;
import com.example.mobile_lab_20521133.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<Block> blocks;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, ArrayList<Block> blocks) {
        this.context = context;
        this.blocks = blocks;
        this.layoutInflater = LayoutInflater.from((context));
    }

    @Override
    public int getCount() {
        return  blocks.size();
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
        view = layoutInflater.inflate(R.layout.block_item_view, null);

        FrameLayout layout = (FrameLayout) view.findViewById(R.id.frameLayout);
        layout.setBackgroundColor(Color.parseColor(blocks.get(i).getColor()));

        TextView text = (TextView) view.findViewById(R.id.txtContent);
        text.setText(blocks.get(i).getContent());

        return view;
    }
}
