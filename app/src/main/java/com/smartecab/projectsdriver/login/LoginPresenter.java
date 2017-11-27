package com.smartecab.projectsdriver.login;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;

import javax.inject.Inject;

import Rest.Model.AuthModel;
import Rest.Service.AuthService;
import Rest.ViewModel.Token;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {
    @Inject
    AuthModel authModel;
    @Inject
    AuthService authService;

    LoginContract.View view;
    CompositeSubscription subscriptions;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        DriverApplication.getAppComponent().inject(this);
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void Login(final String userName, String password) {
        view.showProgress("Logging in....");
        authModel.LoginUser(userName, password, "password", new ModelCallback<Token>() {
            @Override
            public void onSuccess(Token response) {
                view.hideProgress();
                view.goToMain();

            }

            @Override
            public void onError(String networkError) {
                view.hideProgress();
                view.onError(networkError);
            }
        });

    }

    @Override
    public void CreateUser() {

    }

    @Override
    public void onStop() {

    }
}
