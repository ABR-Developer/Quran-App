package com.abr.quranapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterReadQuran extends RecyclerView.Adapter <RecyclerViewAdapterReadQuran.ViewHolder> {
    Context context;
    ArrayList<String> ayahArabic;
    ArrayList<String> ayahUrdu;
    ArrayList<String> ayahEnglish;

    public RecyclerViewAdapterReadQuran(Context ctxt,ArrayList<String> ayahArabic, ArrayList<String> ayahUrdu,ArrayList<String> ayahEnglish)
    {
        this.context = ctxt;
        this.ayahArabic = ayahArabic;
        this.ayahUrdu = ayahUrdu;
        this.ayahEnglish = ayahEnglish;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_read_quran_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ayaArabic = ayahArabic.get(position);
        String ayaUrdu = ayahUrdu.get(position);
        String ayaEnglish = ayahEnglish.get(position);
        holder.AyahArabic.setText(ayaArabic);
        holder.AyahUrdu.setText(ayaUrdu);
        holder.AyahEnglish.setText(ayaEnglish);
    }

    @Override
    public int getItemCount() {
        return ayahArabic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView AyahArabic;
        TextView AyahUrdu;
        TextView AyahEnglish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AyahArabic = itemView.findViewById(R.id.read_arabic_text);
            AyahUrdu = itemView.findViewById(R.id.read_urdu_text);
            AyahEnglish = itemView.findViewById(R.id.read_english_text);
        }
    }
}
