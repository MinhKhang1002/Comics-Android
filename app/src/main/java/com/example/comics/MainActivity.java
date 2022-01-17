package com.example.comics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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
                        Toast.makeText(MainActivity.this, "Home Fragment", Toast.LENGTH_SHORT).show();
                        fragment=new HomeFragment();
                        Log.d("123",fragment+"");
                        break;
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Search Fragment", Toast.LENGTH_SHORT).show();
                        fragment=new HomeFragment1();
                        break;
                    case R.id.action_library:
                        Toast.makeText(MainActivity.this, "Library Fragment", Toast.LENGTH_SHORT).show();
                        fragment=new HomeFragment2();
                        break;
                    case R.id.action_mine:
                        Toast.makeText(MainActivity.this, "Mine Fragment", Toast.LENGTH_SHORT).show();
                        fragment=new HomeFragment3();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
                return true;
            }


        });

    }
}