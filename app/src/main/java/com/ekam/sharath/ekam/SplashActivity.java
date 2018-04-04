package com.ekam.sharath.ekam;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import util.AppConstants;
import util.SharedPrefHelper;

public class SplashActivity extends AppCompatActivity {

    private Activity activity = SplashActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(SharedPrefHelper.getSharedPreferenceString(activity, AppConstants.PREF_UID,""))){
                    startActivity(new Intent(activity,PhoneNoVerificationActivity.class));
                }else startActivity(new Intent(activity, MainActivity.class));

                finish();
            }
        },4000);
    }
}
