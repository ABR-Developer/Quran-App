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

public class SurahRecyclerViewAdapter extends RecyclerView.Adapter <SurahRecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<String> surahNameEng;
    ArrayList<String> surahNameUrdu;

    public SurahRecyclerViewAdapter(Context ctxt, ArrayList<String> surahNameEng, ArrayList<String> surahNameUrdu){
        this.context=ctxt;
        this.surahNameEng = surahNameEng;
        this.surahNameUrdu = surahNameUrdu;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_list_view_surah_name,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String surahEng = surahNameEng.get(position);
        String surahUrdu = surahNameUrdu.get(position);
        holder.SurahNameEng.setText(surahEng);
        holder.SurahNameUrdu.setText(surahUrdu);
    }

    @Override
    public int getItemCount() {
        return surahNameEng.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView SurahNameEng;
        TextView SurahNameUrdu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SurahNameEng = itemView.findViewById(R.id.surahNameEng);
            SurahNameUrdu = itemView.findViewById(R.id.surahNameUrdu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            toAyahList(position);
            Toast.makeText(context, "ID: " +String.valueOf(position), Toast.LENGTH_SHORT).show();

        }
        private void toAyahList(int ii)
        {
            Intent intent = new Intent(context, ayahActivity.class);
            intent.putExtra("surahIndex",String.valueOf(ii));
            context.startActivity(intent);
        }
    }
}
