package karzhavin.newspaper.service.user;

import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;

public interface IUserService {
    User createUser(UserDto userDto);
    User getUserById(Integer userId);
    User getUserByUserProfileId(Integer userProfileId);
    User updateUser(Integer userId, UserDto userDto);
    void deleteUserById(Integer userId);
}
