package Rest.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;

import javax.inject.Inject;

import Rest.Service.AuthService;
import Rest.Service.DriverService;
import Rest.ViewModel.Profile;
import Rest.ViewModel.Token;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 11/21/2017.
 */

public class DriverModel {
    @Inject
    DriverService driverService;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Context context;

    public DriverModel(){
        DriverApplication.getAppComponent().inject(this);
    }

    public Subscription getDriverProfile(String Id, final ModelCallback<Profile> callback){
        return driverService.getProfile(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {
                    @Override
                    public void onCompleted() {  }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t.getMessage());
                    }

                    @Override
                    public void onNext(Profile profile) { callback.onSuccess(profile); }
                });
    }
}
