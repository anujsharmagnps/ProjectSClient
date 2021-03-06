package Rest.Service;

import Rest.ViewModel.Token;
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
    @FormUrlEncoded
    @POST("token")
    Observable<Token> validateUser(@Field("username") String userName, @Field("password") String password, @Field("grant_type") String grant_type );

    @GET("user/{value}/getById")
    Observable<User> getUserById(@Path("value") String FbId);

    @FormUrlEncoded
    @POST("user/{Id}/RegisterDevice")
    Observable<User> RegisterDevice(@Field("UserId") String UserId, @Field("DeviceToken") String token);

    @FormUrlEncoded
    @POST("user/{Id}/UnRegisterDevice/")
    Observable<User> UnRegisterDevice(@Field("UserId") String UserId, @Field("DeviceToken") String token);
}
