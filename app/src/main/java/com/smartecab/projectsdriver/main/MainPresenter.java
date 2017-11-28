package com.smartecab.projectsdriver.main;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;

import javax.inject.Inject;

import Rest.Model.AuthModel;
import Rest.Model.DriverModel;
import Rest.ViewModel.Profile;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pc on 11/27/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;
    private final CompositeSubscription subscriptions;
    @Inject
    DriverModel driverModel;
    @Inject
    AuthModel authModel;

    MainPresenter(MainContract.View view) {
        this.view = view;
        DriverApplication.getAppComponent().inject(this);
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        subscriptions.unsubscribe();
    }

    @Override
    public void getDriverProfile() {

        authModel.getUserData();

        driverModel.getDriverProfile(authModel.id, new ModelCallback<Profile>() {
            @Override
            public void onSuccess(Profile response) {
                view.showDriverProfile(response);

            }

            @Override
            public void onError(String networkError) {

            }
        });
    }

    @Override
    public void toggleDriverOnline(boolean status) {

    }

    @Override
    public void getDriverPhoto() {

    }

    @Override
    public void Logout() {

        this.authModel.cleanUserdata();
        this.view.navigateToLogin();
    }
}
