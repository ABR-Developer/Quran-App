package com.abr.quranapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterReadSurah extends ArrayAdapter {
    private final Activity context;
    private final int[] SurahId;
    private final String[] SurahNameEnglish;
    private final String[] SurahNameUrdu;

    public CustomAdapterReadSurah(Activity context, String[] SurahNameEnglish,String[] SurahNameUrdu, int[] SurahId) {
        super(context, R.layout.activity_custom_read_surah_list, SurahNameEnglish);
        this.context=context;
        this.SurahNameEnglish = SurahNameEnglish;
        this.SurahNameUrdu = SurahNameUrdu;
        this.SurahId = SurahId;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint("ViewHolder") View singleEntityView=inflater.inflate(R.layout.activity_custom_read_surah_list, null,true);
        TextView number =  singleEntityView.findViewById(R.id.read_surah_name_id);
        TextView nameEnglish =  singleEntityView.findViewById(R.id.read_suran_name_english);
        TextView nameUrdu =  singleEntityView.findViewById(R.id.read_surah_name_urdu);
        number.setText("Surah Number : " + Integer.toString(SurahId[position]));
        nameEnglish.setText(SurahNameEnglish[position]);
        nameUrdu.setText(SurahNameUrdu[position]);
        return singleEntityView;
    }
}

