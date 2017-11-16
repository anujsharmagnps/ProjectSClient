package com.smartecab.projectsdriver.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    if(!isInternetAvailable()) {
                        Toast.makeText(SplashActivity.this, "Not connected to internet!!", Toast.LENGTH_SHORT).show();
                        return  ;
                    }
                    Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(myIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
    public boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {

        }
        return false;
    }
}
