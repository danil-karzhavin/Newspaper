package karzhavin.newspaper.service.user;

import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;

public interface IUserProfileService {
    UserProfile getUserProfileByUserId(Integer userId);
    UserProfile getUserProfileById(Integer id);
    UserProfile createUserProfile(UserProfileDto userProfileDto);
    UserProfile updateUserProfileById(UserProfileDto userProfileDto);
    void deleteUserProfileById(Integer userProfileId);
}