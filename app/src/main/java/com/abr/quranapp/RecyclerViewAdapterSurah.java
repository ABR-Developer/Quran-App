package com.abr.quranapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterSurah extends RecyclerView.Adapter <RecyclerViewAdapterSurah.ViewHolder> {
    Context context;
    ArrayList<String> SurahNumber;
    ArrayList<String> SurahNameEnglish;
    ArrayList<String> SurahNameUrdu;

    public RecyclerViewAdapterSurah(Context ctxt,ArrayList<String> SurahNumber, ArrayList<String> SurahNameEnglish,ArrayList<String> SurahNameUrdu)
    {
        this.context = ctxt;
        this.SurahNumber = SurahNumber;
        this.SurahNameEnglish = SurahNameEnglish;
        this.SurahNameUrdu = SurahNameUrdu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_read_surah_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String surahNumber = SurahNumber.get(position);
        String surahNameEnglish = SurahNameEnglish.get(position);
        String surahNameUrdu = SurahNameUrdu.get(position);
        holder.SurahNumber.setText(surahNameEnglish);
        holder.SurahNameEnglish.setText(surahNameEnglish);
        holder.SurahNameUrdu.setText(surahNameUrdu);
    }

    @Override
    public int getItemCount() {
        return SurahNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView SurahNumber;
        TextView SurahNameEnglish;
        TextView SurahNameUrdu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SurahNumber = itemView.findViewById(R.id.read_surah_name_id);
            SurahNameEnglish = itemView.findViewById(R.id.read_suran_name_english);
            SurahNameUrdu = itemView.findViewById(R.id.read_surah_name_urdu);
        }
    }
}
