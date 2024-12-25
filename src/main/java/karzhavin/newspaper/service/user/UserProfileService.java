package karzhavin.newspaper.service.user;

import karzhavin.newspaper.Exception.user.UserProfile.UserProfileDtoException;
import karzhavin.newspaper.Exception.user.UserProfile.UserProfileNotFoundException;
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
    public UserProfileDto getUserProfileDtoByUserId(Integer userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);

        if (userProfile.isEmpty()){
            throw new UserProfileNotFoundException("Not found user profile with such user id");
        }
        return getUserProfileDtoById(userProfile.get().getId());
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
    public UserProfileDto getUserProfileDtoById(Integer id) {
        UserProfileDto userProfileDto = new UserProfileDto();
        UserProfile userProfile = getUserProfileById(id);
        BeanUtils.copyProperties(userProfile, userProfileDto);
        return userProfileDto;
    }

    @Override
    public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
        if(userProfileDto.getUserId() == null){
            throw new UserProfileDtoException("Field 'userId' is null");
        }
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userProfileDto, userProfile);

        User user = userService.getUserById(userProfileDto.getUserId());
        // userProfile.setUserId(userId);
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);
        return getUserProfileDtoById(userProfile.getId());
    }

    @Override
    public UserProfileDto updateUserProfileById(UserProfileDto userProfileDto) {
        UserProfile userProfile = getUserProfileById(userProfileDto.getId());
        BeanUtils.copyProperties(userProfileDto, userProfile, new String[] {"id", "userId"});
        userProfileRepository.save(userProfile);
        return getUserProfileDtoById(userProfile.getId());
    }

    @Override
    public void deleteUserProfileById(Integer userProfileId) {
        userProfileRepository.deleteById(userProfileId);
    }
}
