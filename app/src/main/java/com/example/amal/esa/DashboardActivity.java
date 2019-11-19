package com.example.amal.esa;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.amal.esa.ui.notification.NotificationFragment;
import com.example.amal.esa.utility.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawerLayout;
    NavController navController;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        drawerLayout = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder( R.id.nav_news,
                R.id.nav_services, R.id.nav_profile,R.id.nav_contact_us, R.id.nav_ad_market,
                R.id.nav_tracking,R.id.nav_notification,
                R.id.nav_term, R.id.nav_send,R.id.nav_logout,R.id.nav_faq)
                .setDrawerLayout(drawerLayout)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_chat:
              //  Intent intent=new Intent(DashboardActivity.this,NotificationActivity.class);
              //  startActivity(intent);
                navController.navigate(R.id.nav_notification);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
        || super.onSupportNavigateUp();

       // return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);

        drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.nav_news:

                navController.navigate(R.id.nav_news);
                break;

            case R.id.nav_services:
               // navController.navigate(R.id.nav_profile);
                navController.navigate(R.id.nav_services);
                break;

            case R.id.nav_profile:
               // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_profile);
                break;

            case R.id.nav_contact_us:
                // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_contact_us);
                break;

            case R.id.nav_ad_market:
                // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_ad_market);
                break;

            case R.id.nav_tracking:
                // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_tracking);
                break;

            case R.id.nav_term:
                // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_term);
                break;

            case R.id.nav_faq:
                // navController.navigate(R.id.nav_home);
                navController.navigate(R.id.nav_faq);
                break;


            case R.id.nav_logout:
                System.out.println("=======logout====");
                Intent intent =new Intent(DashboardActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                SharedPrefManager sharedPrefManager=new SharedPrefManager();
                sharedPrefManager.logOut(DashboardActivity.this);
                break;

          /*  case R.id.third:
                navController.navigate(R.id.thirdFragment);
                break;*/

        }
        return true;


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
