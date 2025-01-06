package karzhavin.newspaper.service.user;

import karzhavin.newspaper.Exception.News.NewsDtoException;
import karzhavin.newspaper.Exception.user.UserDtoException;
import karzhavin.newspaper.Exception.user.UserNotFoundException;
import karzhavin.newspaper.Exception.user.UserProfile.UserProfileDtoException;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.repository.user.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user, new String[] {"id"});
        userRepository.save(user);
        return getUserDtoById(user.getId());
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
    public UserDto getUserDtoById(Integer id) {
        User user = getUserById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    // я не знаю зачем мне этот метод
    @Override
    public List<UserDto> getAllUsersDto() {
        return userRepository.findAllUserDto();
    }

    @Override
    public UserDto getUserByUserDtoProfileId(Integer userProfileId) {
        Optional<User> user = userRepository.findByUserProfileId(userProfileId);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found with such user profile id");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user.get(), userDto);
        return userDto;
    }

    @Override
    public UserDto getUserDtoByEmail(Map<String, Object> data) {
        String email = data.get("email").toString();
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new UserDtoException("Field 'id' is null");
        }
        User user = getUserById(userDto.getId());
        BeanUtils.copyProperties(userDto, user, new String[] {"id"});
        userRepository.save(user);
        return getUserDtoById(user.getId());
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }
}