package com.smartecab.projectsdriver.Listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class LocationUpdateListener implements LocationListener {
    Context context;
    public LocationUpdateListener(Context context){
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Location changed", Toast.LENGTH_SHORT).show();
        // update your marker here
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Provider Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Provider Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Status Changed", Toast.LENGTH_SHORT).show();
    }

}
