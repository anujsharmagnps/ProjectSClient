package Rest.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;

import javax.inject.Inject;

import Rest.Service.AuthService;
import Rest.ViewModel.Profile;
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
    public static final String USER_ID = "id";
    public static final String ACCESS_TOKEN = "token";
    public static final String FIREBASE_TOKEN = "FirebaseToken";
    public static String id;
    public static String access_Token;
    public static String firebase_Token;

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

        if (id != "") {
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
                    public void onNext(Token token) {  saveUserData(token.Id, token.access_token); callback.onSuccess(token);}

                });


    }

    public void Logout(final ModelCallback<User> callback) {
        saveUserData("","");
        UnRegisterDevicetoUser(callback);
    }

    private Subscription UnRegisterDevicetoUser(final ModelCallback<User> callback) {
        return authService.UnRegisterDevice(id  ,firebase_Token)
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
        return authService.RegisterDevice(id, firebase_Token)
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

    private void saveUserData(String UserId, String acces_token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.id = UserId;
        this.access_Token = acces_token;

        editor.putString(USER_ID, UserId);
        editor.putString(ACCESS_TOKEN, acces_token);
        editor.apply();
    }

    public void getUserData() {
        id = sharedPreferences.getString(USER_ID, "");
        access_Token = sharedPreferences.getString(ACCESS_TOKEN, "");
    }

    public void cleanUserdata(){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.remove(access_Token);
        editor.commit();
    }
}
