package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.explore);
    }

    ExploreFragment exploreFragment = new ExploreFragment();
    MyBooksFragment myBooksFragment = new MyBooksFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.explore:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, exploreFragment).commit();
                return true;

            case R.id.myBooks:
                startActivity(new Intent(DashboardActivity.this, Map.class));
                return true;

            case R.id.ticket:
                // tambahkan kodingan pindah fragment disini
                startActivity(new Intent(DashboardActivity.this, RewardActivity.class));
                return true;

            case R.id.profile:
                // tambahkan kodingan pindah fragment disini
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.search) {
//            Toast.makeText(this, "Ini search", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.align_justify) {
//            Toast.makeText(this, "Ini sidebar", Toast.LENGTH_SHORT).show();
//        }
        return super.onOptionsItemSelected(item);
    }
}