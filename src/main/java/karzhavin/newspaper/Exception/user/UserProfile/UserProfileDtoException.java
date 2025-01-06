package karzhavin.newspaper.Exception.user.UserProfile;

public class UserProfileDtoException extends RuntimeException {
    public UserProfileDtoException(String message){
        super(message);
    }
    public UserProfileDtoException(){
        super("");
    }
}