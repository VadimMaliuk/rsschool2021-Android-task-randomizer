package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentSendDataListener, SecondFragment.OnFragmentBackDataListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment, "TAG_FIRST")
                .commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, "TAG_SECOND")
                .commit();
    }

    @Override
    public void onSendData(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackData(int random) {
        openFirstFragment(random);
    }

    @Override
    public void onBackPressed() {
        SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag("TAG_SECOND");
        if(secondFragment != null)
        {
            secondFragment.getFragmentBackDataListener().onBackData(secondFragment.getRandomValue());
        }
        else
        {
            finish();
        }
    }
}
