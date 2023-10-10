/*
package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.demoapp.MainActivity;
import com.example.demoapp.R;


public class CustomAdapter extends BaseAdapter {

    public CustomAdapter(Context context, ) {
    }

    @Override

    public int getCount() {
        return msg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtMsg;
        ImageButton btnEdit, btnDelete;
        CardView cardview;
        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.single_data, viewGroup, false);
        txtMsg = view.findViewById(R.id.txtMsg);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);
        cardview = view.findViewById(R.id.cardview);


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        txtMsg.setText(msg[i]);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView(v, id[i]);
                displaydata();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog(i);
            }
        });
        return view;
    }
}



*/
