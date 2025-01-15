package karzhavin.newspaper.Exception.user;

import karzhavin.newspaper.Exception.user.UserProfile.UserProfileDtoException;

public class UserDtoException extends RuntimeException{
    public UserDtoException(String message){
        super(message);
    }
}
