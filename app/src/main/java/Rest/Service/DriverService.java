package Rest.Service;

import Rest.ViewModel.Profile;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by pc on 11/21/2017.
 */

public interface DriverService {
    @GET("/api/profile/{Id}")
    Observable<Profile> getProfile(@Path("Id") String Id);
}
