package com.abr.quranapp;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;

public class bynameList extends AppCompatActivity{
    private RecyclerView recyclerViewSurah;
    private SurahRecyclerViewAdapter surahRecyclerViewAdapter;
    private ArrayList<String> surahNameEng;
    private ArrayList<String> surahNameUrdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.byname_list);

        NavigationView navigationView;
        DrawerLayout drawerLayout;
        Toolbar toolbar;
        ActionBarDrawerToggle toggle;
        recyclerViewSurah = findViewById(R.id.recyclerViewSurah);
        recyclerViewSurah.setHasFixedSize(true);
        recyclerViewSurah.setLayoutManager(new LinearLayoutManager(this));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(bynameList.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
//                    case R.id.read_quran :
//                        Intent intent1 = new Intent(bynameList.this, bynameList.class);
//                        startActivity(intent1);
//                        //drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.home :
//                        Intent intent2 = new Intent(bynameList.this, MainActivity.class);
//                        startActivity(intent2);
//                        break;
//
//                    case R.id.search:
//                        Intent intent3 = new Intent(bynameList.this, SearchFunction.class);
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
        db.DBSurahNames();
        surahNameEng = new ArrayList<>();
        surahNameUrdu = new ArrayList<>();

        surahNameEng = db.getSurahNameEng();
        surahNameUrdu = db.getSurahNameUrdu();

        surahRecyclerViewAdapter = new SurahRecyclerViewAdapter(this,surahNameEng,surahNameUrdu);
        recyclerViewSurah.setAdapter(surahRecyclerViewAdapter);

    }

    DrawerLayout drawerLayout;
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
