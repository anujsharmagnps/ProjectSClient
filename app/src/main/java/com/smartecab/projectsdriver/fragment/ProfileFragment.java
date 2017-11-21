package com.smartecab.projectsdriver.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartecab.projectsdriver.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private static final int ACTIVITY_STARTS_CAMERA_APP = 0;
    Button btnChangePhoto;
    private Context context;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnChangePhoto = (Button) view.findViewById(R.id.btn_change_pic);
        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M ) {
                    checkPermission(v);
                }
            }
        });
        context = this.getContext();
        return view;
    }

    public void takePhoto(View view) {
        Intent callCameraIntent = new Intent();
        callCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(callCameraIntent, ACTIVITY_STARTS_CAMERA_APP);
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        if (requestcode == ACTIVITY_STARTS_CAMERA_APP && resultcode == RESULT_OK) {

        }
    }

    public void checkPermission(View v){
        if(ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    123);

        }else{
            takePhoto(v);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(grantResults.length > 0 ){
////&& permissions[0]==PackageManager.PERMISSION_GRANTED
//        }else{
//            //redirect to settings page or ask permission again
//        }
//    }
}
