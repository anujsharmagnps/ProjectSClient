package com.smartecab.projectsdriver.main;

import com.smartecab.projectsdriver.Base.BasePresenter;
import com.smartecab.projectsdriver.Base.BaseView;

import Rest.ViewModel.Profile;

/**
 * Created by pc on 11/21/2017.
 */

public interface MainContract {
    public interface Presenter extends BasePresenter {
        void getDriverProfile();

        void toggleDriverOnline(boolean status);

        void getDriverPhoto();

        void Logout();

    }

    public interface View extends BaseView {
        void showDriverProfile(Profile profile);

        void showBlocking(Profile profile);

        void navigateToLogin ();

    }

}
