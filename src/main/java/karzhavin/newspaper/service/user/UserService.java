package karzhavin.newspaper.service.user;

import karzhavin.newspaper.Exception.user.UserNotFoundException;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.repository.user.IUserRepository;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class UserService implements IUserService {
    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found with such id");
        }
        return user.get();
    }

    @Override
    public User getUserByUserProfileId(Integer userProfileId) {
        Optional<User> user = userRepository.findByUserProfileId(userProfileId);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found with such user profile id");
        }
        return user.get();
    }

    @Override
    public User updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.getById(userId);
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }
}