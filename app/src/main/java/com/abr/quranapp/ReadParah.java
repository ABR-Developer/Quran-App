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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ReadParah extends AppCompatActivity {
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
        setContentView(R.layout.activity_read_parah);

        toolbar = findViewById(R.id.ReadParahToolbar);
//        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.ReadParah_nav);
        drawerLayout=findViewById(R.id.ReadParahDrawer);

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
                        Toast.makeText(getApplicationContext(),"Read Parah to Home",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadParah.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read:
                        Toast.makeText(getApplicationContext(),"Read Parah to Read Quran",Toast.LENGTH_SHORT).show();
                        intent = new Intent(ReadParah.this, ReadQuran.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_parah:
                        Toast.makeText(getApplicationContext(),"Read Parah to Read Parah",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadParah.this, ReadParah.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_read_by_surah:
                        Toast.makeText(getApplicationContext(),"Read Parah to Read Surah",Toast.LENGTH_LONG).show();
                        intent = new Intent(ReadParah.this, ReadSurah.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


        ArrayList<Integer> count = new ArrayList<>();
        for(int i =0 ; i < 31; i++)
        {
            count.add(i);
        }

        RecyclerView recyclerView = findViewById(R.id.read_parah_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterParah recyclerViewAdapter = new RecyclerViewAdapterParah(this,count);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}