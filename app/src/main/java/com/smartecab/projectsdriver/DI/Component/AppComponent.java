package com.smartecab.projectsdriver.DI.Component;

import com.smartecab.projectsdriver.Base.BaseActivity;
import com.smartecab.projectsdriver.DI.Module.AppModule;
import com.smartecab.projectsdriver.Listener.AppReceiver;
import com.smartecab.projectsdriver.login.LoginPresenter;
import com.smartecab.projectsdriver.main.MainPresenter;

import javax.inject.Singleton;

import Rest.Model.AuthModel;
import Rest.Model.DriverModel;
import dagger.Component;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    //Presenter
    void inject(LoginPresenter loginPresenter);
    void inject(MainPresenter mainPresenter);
    //Model
    void inject(AuthModel authModel);
    void inject(DriverModel driverModel);

    void inject(BaseActivity baseActivity);

    void inject(AppReceiver appReceiver);
}
