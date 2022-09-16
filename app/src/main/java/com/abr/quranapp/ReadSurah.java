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

public class ReadSurah extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_surah);

        toolbar = findViewById(R.id.ReadSurahToolbar);
//        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.ReadSurah_nav);
        drawerLayout=findViewById(R.id.ReadSurahDrawer);

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
                        Toast.makeText(getApplicationContext(),"Read Surah to Home",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadSurah.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_search_ayah:
                        Toast.makeText(getApplicationContext(),"Read Surah to Search Ayah",Toast.LENGTH_LONG).show();
//                        intent = new Intent(ReadSurah.this, SearchAyah.class);
//                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read:
                        Toast.makeText(getApplicationContext(),"Read Surah to Read Quran",Toast.LENGTH_SHORT).show();
                        intent = new Intent(ReadSurah.this, ReadQuran.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_parah:
                        Toast.makeText(getApplicationContext(),"Read Surah to Read Parah",Toast.LENGTH_LONG).show();
//                        intent = new Intent(ReadSurah.this, ReadParah.class);
//                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_surah:
                        Toast.makeText(getApplicationContext(),"Read Surah to Read Surah",Toast.LENGTH_LONG).show();
//                        intent = new Intent(ReadSurah.this, ReadSurah.class);
//                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.read_surah_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        db.DBSurahNames();
        ArrayList<String> surahNameEnglish = db.getSurahNameEnglish();
        ArrayList<String>  surahNameUrdu = db.getSurahNameUrdu();

        RecyclerViewAdapterSurah recyclerViewAdapter = new RecyclerViewAdapterSurah(this,surahNameEnglish,surahNameUrdu);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}