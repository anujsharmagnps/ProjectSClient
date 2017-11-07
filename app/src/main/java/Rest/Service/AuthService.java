package Rest.Service;

import Rest.ViewModel.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public interface AuthService {

    @POST("/token")
    Observable<User> validateUser(@Field("username") String userName, @Field("password") String password);

    @GET("user/{value}/getByFBId")
    Observable<User> getUserByFBId(@Path("value") String FbId);

    @GET("user/{value}/getByUserName")
    Observable<User> getUserByUserName(@Path("value") String UserName);

    @FormUrlEncoded
    @POST("user/1/RegisterUser")
    Observable<User> createUser(@Field("UserName") String UserName, @Field("FacebookId") String FacebookId);

    @FormUrlEncoded
    @POST("user/{Id}/RegisterDevice")
    Observable<User> RegisterDevice(@Field("UserId") String UserId, @Field("DeviceToken") String token);

    @FormUrlEncoded
    @POST("user/{Id}/UnRegisterDevice/")
    Observable<User> UnRegisterDevice(@Field("UserId") String UserId, @Field("DeviceToken") String token);
}
