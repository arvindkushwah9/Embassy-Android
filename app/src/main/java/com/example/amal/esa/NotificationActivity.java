package com.example.amal.esa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.amal.esa.ui.news.ViewNewsFragment;
import com.example.amal.esa.ui.notification.NotificationFragment;
import com.example.amal.esa.ui.passrenewal.PassRenewalFragment;
import com.example.amal.esa.ui.services.AddServiceFragment;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);
        switch (key) {
            case 0:
                Fragment fragment = new PassRenewalFragment();
                replaceFragment(fragment);
                break;

            case 1:
                String title=intent.getStringExtra("title");
                String description=intent.getStringExtra("description");

                Bundle bundle=new Bundle();
                Fragment fragment1 = new ViewNewsFragment();
                bundle.putString("title",title);
                bundle.putString("description",description);
                fragment1.setArguments(bundle);
                replaceFragment(fragment1);
                break;

            case 2:
                Fragment fragment2 = new AddServiceFragment();
                replaceFragment(fragment2);
                break;
        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
