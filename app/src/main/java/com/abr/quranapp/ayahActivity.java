package com.abr.quranapp;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;

public class ayahActivity extends AppCompatActivity {
    RecyclerView recyclerViewAyah;
    AyahRecyclerViewAdapter ayahRecyclerViewAdapter;
    ArrayList<String> ayahArabic;
    ArrayList<String> ayahUrdu;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle toggle;

//    @Override
//    public void onBackPressed(){
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//            Toast.makeText(getApplicationContext(),"Start", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(),"End",Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayah);

        recyclerViewAyah = findViewById(R.id.recyclerViewAyah);
        recyclerViewAyah.setHasFixedSize(true);
        recyclerViewAyah.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        int index=Integer.parseInt(intent.getStringExtra("surahIndex"));
        Toast.makeText(this, "Index: " + index, Toast.LENGTH_SHORT).show();

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
//                    case R.id.read_quran :
//                        Intent intent1 = new Intent(ayahActivity.this, bynameList.class);
//                        startActivity(intent1);
//                        //drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.home :
//                        Intent intent2 = new Intent(ayahActivity.this, MainActivity.class);
//                        startActivity(intent2);
//                        break;
//
//                    case R.id.search:
//                        Intent intent3 = new Intent(ayahActivity.this, SearchFunction.class);
//                        startActivity(intent3);
//                        break;

                }

                return true;
            }
        });

        DBMain db;
        db = new DBMain(getApplicationContext());

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

    }

    int counter =0;
    @Override
    public void onBackPressed(){
        counter++;
        if(counter == 2)
        {
            super.onBackPressed();
        }

    }
}