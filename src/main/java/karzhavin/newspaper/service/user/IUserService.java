package karzhavin.newspaper.service.user;

import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;

import java.util.List;
import java.util.Map;

public interface IUserService {
    UserDto createUser(UserDto userDto);

    User identicateAndAuthenticate(String email, String password);
    User getUserById(Integer userId);
    UserDto getUserDtoById(Integer Id);
    List<UserDto> getAllUsersDto();
    UserDto getUserDtoByEmail(Map<String, Object> data);
    User getUserByEmail(String email);

    UserDto getUserByUserDtoProfileId(Integer userProfileId);
    UserDto updateUser(UserDto userDto);
    void deleteUserById(Integer userId);
}
