package Rest.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.smartecab.projectsdriver.DriverApplication;
import com.smartecab.projectsdriver.ModelCallback;
import com.smartecab.projectsdriver.main.MainPresenter;

import javax.inject.Inject;

import Rest.Service.AuthService;
import Rest.Service.DriverService;
import Rest.ViewModel.Profile;
import Rest.ViewModel.Token;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static Rest.Model.AuthModel.USER_ID;

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
    public String DRIVER_NAME = "name";
    public String CONTACT_NO = "no";
    public String RATING = "rating";
    public String VEHICLE_ID = "id";
    public String REGISTRATION_NO = "register no";
    public String VEHICLE_TYPE_ID = "vechivle type id";
    public String VEHICLE_TYPE = "type";
    public String MANUFACTURER = "manufacturer";
    public String MODEL = "asas";
    public String name;
    public String contactNumber;
    public int rating;
    public String vehicleId;
    public String registrationNumber;
    public int vehicleTypeId;
    public String vehicleType;
    public String manufacturer;
    public String modle;
    ;
    ;

    public DriverModel() {

        DriverApplication.getAppComponent().inject(this);
    }

    public Subscription getDriverProfile(String Id,final ModelCallback<Profile> callback) {
        return driverService.getProfile(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t.getMessage());
                    }

                    @Override
                    public void onNext(Profile profile) {
                        saveUserData(profile.name,profile.contactNumber,profile.rating,profile.vehicleId,profile.registrationNumber,profile.vehicleTypeId,profile.vehicleType,profile.manufacturer,profile.model);
                        callback.onSuccess(profile);
                    }
                });
    }

    private void saveUserData(String name, String contact_no, int rating, String vehicleId, String registration_number, int vehicle_type_id,String vehicle_type, String manufacturer, String modle) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.name = name;
        this.contactNumber = contact_no;
        this.rating = rating;
        this.vehicleId = vehicleId;
        this.registrationNumber = registration_number;
        this.vehicleTypeId = vehicle_type_id;
        this.vehicleType = vehicle_type;
        this.manufacturer = manufacturer;
        this.modle = modle;
        editor.putString(CONTACT_NO, contact_no);
        editor.putString(DRIVER_NAME, name);
        editor.putInt(RATING, rating);
        editor.putString(VEHICLE_ID, vehicleId);
        editor.putString(REGISTRATION_NO, registration_number);
        editor.putInt(VEHICLE_TYPE_ID, vehicle_type_id);
        editor.putString(VEHICLE_TYPE,vehicle_type);
        editor.putString(MANUFACTURER,manufacturer);
        editor.putString(MODEL,modle);
        editor.apply();
    }

    public void getUserData() {
        name = sharedPreferences.getString(DRIVER_NAME, "");
        contactNumber = sharedPreferences.getString(CONTACT_NO, "");
        rating = sharedPreferences.getInt(RATING, 0);
        vehicleId = sharedPreferences.getString(VEHICLE_ID, "");
        registrationNumber = sharedPreferences.getString(REGISTRATION_NO, "");
        vehicleTypeId = sharedPreferences.getInt(VEHICLE_TYPE_ID,0);
        vehicleType = sharedPreferences.getString(VEHICLE_TYPE,"");
        manufacturer = sharedPreferences.getString(MANUFACTURER, "");
        modle = sharedPreferences.getString(MODEL,"");
    }
}

