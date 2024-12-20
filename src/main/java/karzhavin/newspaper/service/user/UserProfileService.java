package karzhavin.newspaper.service.user;

import karzhavin.newspaper.Exception.user.UserProfileNotFoundException;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;
import karzhavin.newspaper.repository.user.IUserProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService implements IUserProfileService{
    IUserProfileRepository userProfileRepository;
    IUserService userService;

    public UserProfileService(IUserProfileRepository userProfileRepository, IUserService userService) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
    }

    @Override
    public UserProfile getUserProfileByUserId(Integer userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);

        if (userProfile.isEmpty()){
            throw new UserProfileNotFoundException("Not found user profile with such user id");
        }
        return userProfile.get();
    }

    @Override
    public UserProfile getUserProfileById(Integer id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isEmpty()){
            throw new UserProfileNotFoundException("Not found user profile with such id");
        }
        return userProfile.get();
    }

    @Override
    public UserProfile createUserProfile(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userProfileDto, userProfile);

        User user = userService.getUserById(userProfileDto.getUserId());
        // userProfile.setUserId(userId);
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Override
    public UserProfile updateUserProfileById(UserProfileDto userProfileDto) {
        UserProfile userProfile = getUserProfileById(userProfileDto.getId());
        BeanUtils.copyProperties(userProfileDto, userProfile);
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Override
    public void deleteUserProfileById(Integer userProfileId) {
        userProfileRepository.deleteById(userProfileId);
    }
}
