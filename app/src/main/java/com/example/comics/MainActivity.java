package com.example.comics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.comics.Fragment.HomeFragment;
import com.example.comics.Fragment.LibraryFragment;
import com.example.comics.Fragment.MineFragment;
import com.example.comics.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_bottom);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=new HomeFragment();
                switch(item.getItemId()){

                    case R.id.action_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.action_search:
                        fragment=new SearchFragment();
                        break;
                    case R.id.action_library:
                        fragment=new LibraryFragment();
                        break;
                    case R.id.action_mine:
                        fragment=new MineFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
                return true;
            }


        });

    }
}