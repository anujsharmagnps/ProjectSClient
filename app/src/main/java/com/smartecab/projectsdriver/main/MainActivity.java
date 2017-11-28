package com.smartecab.projectsdriver.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.smartecab.projectsdriver.Base.BaseActivity;
import com.smartecab.projectsdriver.Base.Constants;
import com.smartecab.projectsdriver.Event.SettingChange;
import com.smartecab.projectsdriver.R;
import com.smartecab.projectsdriver.fragment.ProfileFragment;
import com.smartecab.projectsdriver.fragment.RideFragment;
import com.smartecab.projectsdriver.login.LoginActivity;
import com.smartecab.projectsdriver.navigation.NavigationFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import Rest.ViewModel.Profile;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private FragmentManager fragmentManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private MainContract.Presenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Toast.makeText(MainActivity.this, "Provider Disabled", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(3 * 1000);
            mLocationRequest.setFastestInterval(3 * 1000);

            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    for (Location location : locationResult.getLocations()) {
                        // Update UI with location data
                        // ...
                        Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                    }
                }

                ;
            };

            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        } catch (SecurityException ex) {

        }

////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationFragment fragLeagueList = new NavigationFragment();
        showFragment(fragLeagueList, "Home", false, false);
        presenter = new MainPresenter(this);
        presenter.getDriverProfile();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void showFragment(Fragment fragment, String tag, boolean addToStack, boolean replace) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (replace)
            transaction.replace(R.id.frag_map, fragment, tag);
        else
            transaction.add(R.id.frag_map, fragment, tag);
        if (addToStack)
            transaction.addToBackStack(tag);
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user_profile) {
            ProfileFragment fragment = new ProfileFragment();
            showFragment(fragment, "profile", true, false);
        } else if (id == R.id.nav_ride) {
            RideFragment fragment = new RideFragment();
            showFragment(fragment, "profile", true, false);
//        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            this.presenter.Logout();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(SettingChange event) {
        if (event.Setting == Constants.GPSSetting) {
            this.checkGPSSetting();
        }
    }

    private void checkGPSSetting() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        Task<LocationSettingsResponse> task =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
//                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
//                    MainActivity.this.startActivity(myIntent);
//                    finish();

                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                showGPSDisabledAlertToUser();
                            } catch (ClassCastException e) {
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("" + "GPS Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(callGPSSettingIntent, 1001);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        checkGPSSetting();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void showDriverProfile(Profile profile) {
        TextView txtDriverName = (TextView) findViewById(R.id.txt_header_diver_name);
        txtDriverName.setText(profile.name);
        RatingBar driverRating = (RatingBar) findViewById(R.id.driver_ratingBar);
        driverRating.setRating(profile.rating);
        showToast(profile.name);
    }

    @Override
    public void showBlocking(Profile profile) {

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }
}
