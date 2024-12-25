package karzhavin.newspaper.service.user;

import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;

public interface IUserProfileService {
    UserProfileDto getUserProfileDtoByUserId(Integer userId);
    UserProfile getUserProfileById(Integer id);
    UserProfileDto getUserProfileDtoById(Integer id);
    UserProfileDto createUserProfile(UserProfileDto userProfileDto);
    UserProfileDto updateUserProfileById(UserProfileDto userProfileDto);
    void deleteUserProfileById(Integer userProfileId);
}