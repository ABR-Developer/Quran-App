package com.abr.quranapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AyahRecyclerViewAdapter extends RecyclerView.Adapter <AyahRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<String> ayahArabic;
    ArrayList<String> ayahUrdu;

    public AyahRecyclerViewAdapter(Context ctxt,ArrayList<String> ayahArabic, ArrayList<String> ayahUrdu)
    {
        this.context = ctxt;
        this.ayahArabic = ayahArabic;
        this.ayahUrdu = ayahUrdu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_list_view_ayah,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ayaArabic = ayahArabic.get(position);
        String ayaUrdu = ayahUrdu.get(position);
        holder.AyahArabic.setText(ayaArabic);
        holder.AyahUrdu.setText(ayaUrdu);
    }

    @Override
    public int getItemCount() {
        return ayahArabic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView AyahArabic;
        TextView AyahUrdu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AyahArabic = itemView.findViewById(R.id.ArabicText);
            AyahUrdu = itemView.findViewById(R.id.UrduText);

        }
    }
}
