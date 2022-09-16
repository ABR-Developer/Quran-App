package com.abr.quranapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterSurah extends RecyclerView.Adapter <RecyclerViewAdapterSurah.ViewHolder> {
    Context context;
    ArrayList<String> SurahNameEnglish;
    ArrayList<String> SurahNameUrdu;

    public RecyclerViewAdapterSurah(Context ctxt, ArrayList<String> SurahNameEnglish,ArrayList<String> SurahNameUrdu)
    {
        this.context = ctxt;
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
        String surahNameEnglish = SurahNameEnglish.get(position);
        String surahNameUrdu = SurahNameUrdu.get(position);
        holder.SurahNameEnglish.setText(surahNameEnglish);
        holder.SurahNameUrdu.setText(surahNameUrdu);
    }

    @Override
    public int getItemCount() {
        return SurahNameEnglish.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView SurahNameEnglish;
        TextView SurahNameUrdu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SurahNameEnglish = itemView.findViewById(R.id.read_suran_name_english);
            SurahNameUrdu = itemView.findViewById(R.id.read_surah_name_urdu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            final Intent intent = new Intent(context, ReadQuran.class);
            intent.putExtra("index",String.valueOf(position));
            intent.putExtra("type","surah");
            context.startActivity(intent);
        }
    }
}
