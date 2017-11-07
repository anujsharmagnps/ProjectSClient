package com.smartecab.projectsdriver.Base;

import android.app.ProgressDialog;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */


public class BaseDialogeFragment extends DialogFragment implements BaseView {
    public ProgressDialog mProgressDialog;

    @Override
    public void showProgress(String message) {

        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public void hideProgress() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void onError(String message) {
        Log.d("retro", message);
    }
}
