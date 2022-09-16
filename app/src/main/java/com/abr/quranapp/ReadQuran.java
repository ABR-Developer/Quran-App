package com.abr.quranapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ReadQuran extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            Toast.makeText(getApplicationContext(),"Start", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"End",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_quran);

        toolbar = findViewById(R.id.ReadQuranToolbar);
//        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.ReadQuran_nav);
        drawerLayout=findViewById(R.id.ReadQuranDrawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home :
                        Toast.makeText(getApplicationContext(),"Read Quran to Home",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadQuran.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_search_ayah:
                        Toast.makeText(getApplicationContext(),"Read Quran to Search Ayah",Toast.LENGTH_LONG).show();
//                        intent = new Intent(ReadSurah.this, SearchAyah.class);
//                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read:
                        Toast.makeText(getApplicationContext(),"Read Quran to Read Quran",Toast.LENGTH_SHORT).show();
                        intent = new Intent(ReadQuran.this, ReadQuran.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_parah:
                        Toast.makeText(getApplicationContext(),"Read Quran to Read Parah",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadQuran.this, ReadParah.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_surah:
                        Toast.makeText(getApplicationContext(),"Read Quran to Read Surah",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadQuran.this, ReadSurah.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

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

        Intent i = getIntent();
        int index;
        String type = i.getStringExtra("type");
        if(Objects.equals(type, "surah"))
        {
            index = Integer.parseInt(i.getStringExtra("index"));
            db.ReadAyahBySurahId(index);
            Toast.makeText(this, "Surah" + Integer.toString(index), Toast.LENGTH_SHORT).show();
        }
        else if(Objects.equals(type, "parah"))
        {
            index = Integer.parseInt(i.getStringExtra("index"));
            db.ReadAyahByParahId(index);
            Toast.makeText(this, "Parah" + Integer.toString(index), Toast.LENGTH_SHORT).show();
        }
        else{
            db.ReadWholeQuran();
            Toast.makeText(this, "Whole" + type, Toast.LENGTH_SHORT).show();
        }

        ArrayList<String> arabic = db.getAyahsArabic();
        ArrayList<String> urdu = db.getAyahsUrdu();
        ArrayList<String> english = db.getAyahsEnglish();

        RecyclerView recyclerView = findViewById(R.id.read_quran_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterReadQuran recyclerViewAdapter = new RecyclerViewAdapterReadQuran(this,arabic,urdu, english);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}