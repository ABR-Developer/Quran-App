package com.abr.quranapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterParah extends RecyclerView.Adapter <RecyclerViewAdapterParah.ViewHolder> {
    Context context;
    ArrayList<Integer> ParahNumber;

    public RecyclerViewAdapterParah(Context ctxt,ArrayList<Integer> ParahNumber)
    {
        this.context = ctxt;
        this.ParahNumber = ParahNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_read_parah_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer parahNumber = ParahNumber.get(position);
        holder.ParahNumber.setText("Para # " + Integer.toString(parahNumber));
    }

    @Override
    public int getItemCount() {
        return ParahNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ParahNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ParahNumber = itemView.findViewById(R.id.read_parah_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            final Intent intent = new Intent(context, ReadQuran.class);
            intent.putExtra("index",String.valueOf(position));
            intent.putExtra("type","parah");
            context.startActivity(intent);
        }
    }
}
