package com.example.amal.esa;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    ListView list;
    String[] web = {
            "Bazar organized in Kuala Lumpur to support Yemeni families in Malaysia"
          //  "Successful female Yemeni business pioneers present their experiences in Malaysia",
         //  "Ba-Homaid explores more Malaysian cooperation in Visa procedures",
           // "Malaysian FM launches fund-raising campaign for Yemen",
            //"Yemeni embassy in Malaysia celebrates the 28th Anniversary of Unity Day May 22"
    } ;
    Integer[] imageId = {
            R.drawable.ic_admarket
            //R.drawable.news2,
            //R.drawable.news3,
            //R.drawable.news4,
            //R.drawable.news5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CustomList adapter = new
                CustomList(HomeActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if ( id== R.id.home)
        {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        if ( id== R.id.contactus)
        {
            Intent intent = new Intent(HomeActivity.this, ContactUs.class);
            startActivity(intent);
        }
        if ( id== R.id.logout)
        {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return false;
    }
}


