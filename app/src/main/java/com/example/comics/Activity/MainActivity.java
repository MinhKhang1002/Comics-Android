package com.example.comics.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.comics.Fragment.HomeFragment;
import com.example.comics.Fragment.LibraryFragment;
import com.example.comics.Fragment.MineFragment;
import com.example.comics.Fragment.SearchFragment;
import com.example.comics.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragment=new HomeFragment();
    Fragment fragmentHome=new HomeFragment();
    Fragment fragmentSearch=new SearchFragment();
    Fragment fragmentLibrary=new LibraryFragment();
    Fragment fragmentMine = new MineFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_bottom);



        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragmentHome).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.action_home:

                        //getSupportFragmentManager().beginTransaction().hide(fragment).show(fragmentHome).commit();
                        fragment = fragmentHome;
                        break;
                    case R.id.action_search:


                        fragment=fragmentSearch;

                        break;
                    case R.id.action_library:
                        fragment=fragmentLibrary;
                        break;
                    case R.id.action_mine:
                        fragment=fragmentMine;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
                return true;
            }


        });
    }


}