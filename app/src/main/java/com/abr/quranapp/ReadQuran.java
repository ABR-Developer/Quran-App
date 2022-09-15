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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home :
                        Toast.makeText(getApplicationContext(),"Return is Clicked",Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(MainActivity.this, BookActivity.class);
//                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_search_ayah:
                        Toast.makeText(getApplicationContext(),"Search Ayah clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_read:
//                        Intent intent = new Intent(MainActivity.this, ReadQuran.class);
//                        startActivity(intent);

                        Toast.makeText(getApplicationContext(),"Read clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_read_by_parah:
                        Toast.makeText(getApplicationContext(),"Read by Parah clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_read_by_surah:
                        Toast.makeText(getApplicationContext(),"Read by Surah clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }
}