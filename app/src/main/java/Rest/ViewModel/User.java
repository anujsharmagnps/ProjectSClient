package Rest.ViewModel;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */

public class User {
    public Integer userId;
    public String userName;
    public Integer profileImage;
    public String email;
    public String PhoneNo;

    public String getUserName()
    {
        return userName;
    }

    public int getProfileImage()
    {
        return profileImage;
    }

    public int getUserId()
    {
        return  userId;
    }

    public String getEmail(){ return email;}

    public String getPhoneNo() {return PhoneNo;}

}
