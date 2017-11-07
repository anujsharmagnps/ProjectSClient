package com.smartecab.projectsdriver.Base;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public interface BaseView {
    void showProgress(String message);

    void hideProgress();

    void onError(String message);
}
