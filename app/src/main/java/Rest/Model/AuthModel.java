package Rest.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;

import javax.inject.Inject;

import Rest.Service.AuthService;
import Rest.ViewModel.Token;
import Rest.ViewModel.User;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public class AuthModel {
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String PHOTO_URL = "photo_url";
    public static final String FIREBASE_TOKEN = "FirebaseToken";
    public static String UserId;
    public static String UserName, PhotoUrl, FirebaseToken;
    public User CurrentUser;
    @Inject
    AuthService authService;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Context context;

    public AuthModel() {
        DriverApplication.getAppComponent().inject(this);
        getUserData();
    }

    public boolean isUserLogin() {
        getUserData();

        if (UserId != "") {
            return true;
        }
        return false;
    }

    public Subscription LoginUser(String userName, String password, String grantType, final ModelCallback<Token> callback){
        return authService.validateUser(userName, password, grantType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Token>() {
                    @Override
                    public void onCompleted() {  }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t.getMessage());
                    }

                    @Override
                    public void onNext(Token token) { callback.onSuccess(token); }
                });

    }

    public void Logout(final ModelCallback<User> callback) {
        saveUserData("","","");
        UnRegisterDevicetoUser(callback);
    }

    private Subscription UnRegisterDevicetoUser(final ModelCallback<User> callback) {
        return authService.UnRegisterDevice(UserId, FirebaseToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        callback.onSuccess(user);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    private Subscription RegisterDevicetoUser(final ModelCallback<User> callback) {
        return authService.RegisterDevice(UserId, FirebaseToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        callback.onSuccess(user);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    private void saveUserData(String UserName, String UserId, String photoUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.UserId = UserId;

        this.UserName = UserName;
        this.PhotoUrl = photoUrl;

        editor.putString(USER_ID, UserId);
        editor.putString(USER_NAME, UserName);
        editor.putString(PHOTO_URL, photoUrl);
        editor.apply();
    }

    private void getUserData() {
        UserId = sharedPreferences.getString(USER_ID, "");
        UserName = sharedPreferences.getString(USER_NAME, "");
        PhotoUrl = sharedPreferences.getString(PHOTO_URL, "");
        FirebaseToken = sharedPreferences.getString(FIREBASE_TOKEN, "");
    }
}
