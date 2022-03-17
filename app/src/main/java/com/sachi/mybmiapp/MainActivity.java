package com.sachi.mybmiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sachi.mybmiapp.BMIFragements.BMIFragment;
import com.sachi.mybmiapp.BMIFragements.HistoryFragment;
import com.sachi.mybmiapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callFragement(new BMIFragment());

        binding.BottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.NavBMI:
                    callFragement(new BMIFragment());
                    break;
                case R.id.NavHistory:
                    callFragement(new HistoryFragment());
                    break;
            }

            return true;
        });


    }


    public void callFragement(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.Frame_Main,fragment);
        transaction.commit();
    }
}