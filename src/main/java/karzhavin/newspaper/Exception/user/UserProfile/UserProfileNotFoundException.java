package karzhavin.newspaper.Exception.user.UserProfile;

public class UserProfileNotFoundException extends RuntimeException {
    public UserProfileNotFoundException(String message){
        super(message);
    }
}