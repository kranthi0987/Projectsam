package com.sanjay.projectsam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.sanjay.projectsam.network.ApplicationUtility;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FullScreencall();
//Fragments initialization for splash page
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SplashFragment f1 = new SplashFragment();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.splashfragment, f1);
        fragmentTransaction.commit();
//        permission checking intialization

        checkPerm();
    }


    private void checkPerm() {
        PermissionListener permissionlistener = new PermissionListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onPermissionGranted() {
//                Toast.makeText(Splash_activity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Timer t = new Timer();
                boolean checkConnection = new ApplicationUtility().checkConnection(SplashActivity.this);
                if (checkConnection) {
                    t.schedule(new splash(), 3000);
                    Toast.makeText(SplashActivity.this,
                            "Internet permission granted", 3000).show();
                } else {
                    Toast.makeText(SplashActivity.this,
                            "connection not found...plz check connection", 3000).show();
                    t.schedule(new splash(), 3000);
                }
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(SplashActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.INTERNET)
                .check();
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    class splash extends TimerTask {
        @Override
        public void run() {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            finish();
            startActivity(i);
        }

    }
}
