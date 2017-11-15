package com.smartecab.projectsdriver.Login;

import com.smartecab.projectsdriver.Base.BasePresenter;
import com.smartecab.projectsdriver.Base.BaseView;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public interface LoginContract {
    public interface Presenter extends BasePresenter {
        void Login(String UserId, String UserName);

        void CreateUser();
    }

    public interface View extends BaseView {
        void goToMain();
    }
}
