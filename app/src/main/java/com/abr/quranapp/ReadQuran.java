package com.abr.quranapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;

public class ReadQuran extends AppCompatActivity {

    RecyclerView recyclerViewAyah;
    AyahRecyclerViewAdapter ayahRecyclerViewAdapter;
    ArrayList<String> ayahArabic;
    ArrayList<String> ayahUrdu;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_quran);

        recyclerViewAyah = findViewById(R.id.recyclerViewAyah);
        recyclerViewAyah.setHasFixedSize(true);
        recyclerViewAyah.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        int index=Integer.parseInt(intent.getStringExtra("surahIndex"));
        Toast.makeText(this, "Index: " + index, Toast.LENGTH_SHORT).show();

        DBMain db = new DBMain(getApplicationContext());
        try {
            db.createDB();
        } catch (IOException ioe) {
            throw new Error("Database not created....");
        }
        try {
            db.openDB();
        } catch (SQLException sqle) {
            throw sqle;
        }
        db.DBAyahs(index);

        ayahArabic = new ArrayList<>();
        ayahUrdu = new ArrayList<>();

        ayahArabic = db.getAyahsArabic();
        ayahUrdu = db.getAyahsUrdu();

        ayahRecyclerViewAdapter = new AyahRecyclerViewAdapter(this,ayahArabic,ayahUrdu);
        recyclerViewAyah.setAdapter(ayahRecyclerViewAdapter);

//        ArrayList<String> data = db.AyahsArabic;
//        Toast.makeText(this,data.get(0),Toast.LENGTH_SHORT).show();
//
//        int size = data.size()/3;
//        String[] arabic = new String[size];
//        String[] urdu = new String[size];
//        String[] english = new String[size];
//        for(int i = 0; i < data.size() ; i++)
//        {
//            arabic[i] = data.get(i);
//            urdu[i] = data.get(i+1);
//            english[i] = data.get(i+2);
//            i++;
//            i++;
//        }
//
//        ListView listView = findViewById(R.id.read_quran_list);
//        CustomAdapterReadQuran customArrayAdapter=new CustomAdapterReadQuran(this,arabic,urdu,english);
//        listView.setAdapter(customArrayAdapter);
    }
}