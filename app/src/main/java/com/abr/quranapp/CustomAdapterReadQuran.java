package com.abr.quranapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterReadQuran extends ArrayAdapter {
    private final Activity context;
    private final String[] ArabicText;
    private final String[] UrduText;
    private final String[] EnglishText;

    public CustomAdapterReadQuran(Activity context, String[] ArabicText, String[] UrduText, String[] EnglishText) {
        super(context, R.layout.activity_custom_read_quran_list, ArabicText);
        this.context=context;
        this.ArabicText =ArabicText;
        this.UrduText =UrduText;
        this.EnglishText = EnglishText;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint("ViewHolder") View singleEntityView=inflater.inflate(R.layout.activity_custom_read_quran_list, null,true);
        TextView arabic =  singleEntityView.findViewById(R.id.read_arabic_text);
        TextView urdu =  singleEntityView.findViewById(R.id.read_urdu_text);
        TextView english =  singleEntityView.findViewById(R.id.read_english_text);
        arabic.setText(ArabicText[position]);
        urdu.setText(UrduText[position]);
        english.setText(EnglishText[position]);
        return singleEntityView;
    }
}
