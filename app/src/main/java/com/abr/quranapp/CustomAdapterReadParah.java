package com.abr.quranapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterReadParah extends ArrayAdapter {
    private final Activity context;
    private final String[] ParahNumber;

    public CustomAdapterReadParah(Activity context, String[] ParahNumber) {
        super(context, R.layout.activity_custom_read_parah_list, ParahNumber);
        this.context=context;
        this.ParahNumber = ParahNumber;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint("ViewHolder") View singleEntityView=inflater.inflate(R.layout.activity_custom_read_parah_list, null,true);
        TextView parah =  singleEntityView.findViewById(R.id.read_parah_number);
        parah.setText("Parah Number : " + ParahNumber[position]);
        return singleEntityView;
    }
}

