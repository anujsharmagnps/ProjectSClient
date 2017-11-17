package com.smartecab.projectsdriver.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smartecab.projectsdriver.Base.BaseFragment;
import com.smartecab.projectsdriver.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView   mMapView;
    View      mView;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mView = inflater.inflate(R.layout.fragment_navigation, container, false);
       return mView;
    }

    @Override
    public void  onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mMapView =(MapView) mView.findViewById(R.id.map);
        if (mMapView !=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }
 @Override
    public  void onMapReady(GoogleMap googleMap) {
     MapsInitializer.initialize(getContext());
     mGoogleMap = googleMap;
     googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
     googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247,-74.044502)).title("statue of liberty").snippet("I hope to go there some day"));
     CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247,-74.044502)).zoom(16).bearing(0).tilt(45).build();
     googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
 }
}
