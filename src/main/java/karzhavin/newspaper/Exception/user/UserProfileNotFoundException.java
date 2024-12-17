package karzhavin.newspaper.Exception.user;

public class UserProfileNotFoundException extends RuntimeException {
    public UserProfileNotFoundException(String message){
        super(message);
    }
}