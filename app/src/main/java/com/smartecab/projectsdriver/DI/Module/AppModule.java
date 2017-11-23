package com.smartecab.projectsdriver.DI.Module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import Rest.Model.AuthModel;
import Rest.Model.DriverModel;
import Rest.Service.AuthService;
import Rest.Service.DriverService;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */
@Module
public class AppModule {
//    private static final String BASE_URL = "http://192.168.1.7/ProjectS.Apps.Driver.API/";
private static final String BASE_URL = "http://103.48.51.159:51421/driverapi/";

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    Retrofit providesRestClient() {
        //return new RestClient(BASE_URL, RestAdapter.LogLevel.FULL);
        //return new RestClient();
        Cache cache = null;
        cache = new Cache(new File("http"), 10 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", 432000))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return restAdapter;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPrefrences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    AuthService providesAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    DriverService providesDriverSrvice(Retrofit retrofit) {
        return retrofit.create(DriverService.class);
    }
    @Provides
    @Singleton
    AuthModel providesAuthModel() {
        return new AuthModel();
    }

    @Provides
    @Singleton
    DriverModel providesDrivermodel() {
        return new DriverModel();
    }

}
