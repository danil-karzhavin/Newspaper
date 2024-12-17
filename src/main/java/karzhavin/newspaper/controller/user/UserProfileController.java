package karzhavin.newspaper.controller.user;

import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;
import karzhavin.newspaper.service.user.IUserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersProfiles")
public class UserProfileController {
    IUserProfileService userProfileService;

    public UserProfileController(IUserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/byUser/")
    ResponseEntity<UserProfile> getUserProfileByUserId(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userProfileService.getUserProfileByUserId(userId));
    }

    @GetMapping("/")
    ResponseEntity<UserProfile> getUserProfileById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userProfileService.getUserProfileById(id));
    }

    @PostMapping("/")
    ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfileDto userProfileDto){
        return ResponseEntity.ok(userProfileService.createUserProfile(userProfileDto));
    }

    @PostMapping("/")
    ResponseEntity<UserProfile> updateUserProfileById(Integer userProfileId, @RequestBody UserProfileDto userProfileDto){
        return ResponseEntity.ok(userProfileService.updateUserProfileById(userProfileId, userProfileDto));
    }

    @DeleteMapping("/")
    void deleteUserProfileById(@PathVariable Integer id){
        userProfileService.deleteUserProfileById(id);
    }
}
