package com.smartecab.projectsdriver.DI.Component;

import com.smartecab.projectsdriver.Base.BaseActivity;
import com.smartecab.projectsdriver.DI.Module.AppModule;
import com.smartecab.projectsdriver.login.LoginPresenter;

import javax.inject.Singleton;

import Rest.Model.AuthModel;
import dagger.Component;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    //Presenter
    void inject(LoginPresenter loginPresenter);

    //Model
    void inject(AuthModel authModel);

    void inject(BaseActivity baseActivity);
}
