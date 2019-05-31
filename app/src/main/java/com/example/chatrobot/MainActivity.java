package com.example.chatrobot;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new UserFragment());
        final TextView mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText(getString(R.string.user_information));
        BottomNavigationView navigation = findViewById(R.id.bnv_main);
        navigation.setSelectedItemId(R.id.item_user);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_message:
                        mTvTitle.setText(getString(R.string.message));
                        replaceFragment(new FriendFragment());
                        return true;
                    case R.id.item_user:
                        mTvTitle.setText(getString(R.string.user_information));
                        replaceFragment(new UserFragment());
                        return true;
                }
                return false;
            }
        });
    }

    //碎片切换
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.ll_main, fragment);
        transaction.commit();
    }

}
