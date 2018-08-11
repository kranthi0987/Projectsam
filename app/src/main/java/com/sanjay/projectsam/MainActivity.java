package com.sanjay.projectsam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    private void receiveData() {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String name = i.getStringExtra("SENDER_KEY");
        setTitle(name);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String sender=this.getIntent().getExtras().getString("SENDER_KEY");
        receiveData();

        //Fragments initialization
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BlankFragment f1 = new BlankFragment();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment, f1);
        fragmentTransaction.commit();
//        Intent i = getIntent();
//        String name = i.getStringExtra("SENDER_KEY");
//        setTitle(name);
    }

}
