package karzhavin.newspaper.controller.user;

import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;
import karzhavin.newspaper.service.user.IUserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userProfile")
public class UserProfileController {
    IUserProfileService userProfileService;

    public UserProfileController(IUserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/byUser/{userId}")
    ResponseEntity<UserProfileDto> getUserProfileByUserId(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userProfileService.getUserProfileDtoByUserId(userId));
    }

    @GetMapping("/{id}")
    ResponseEntity<UserProfile> getUserProfileById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userProfileService.getUserProfileById(id));
    }

    @PostMapping("/")
    ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto){
        return ResponseEntity.ok(userProfileService.createUserProfile(userProfileDto));
    }

    @PutMapping("/")
    ResponseEntity<UserProfileDto> updateUserProfileById(@RequestBody UserProfileDto userProfileDto){
        return ResponseEntity.ok(userProfileService.updateUserProfileById(userProfileDto));
    }

    @DeleteMapping("/{id}")
    void deleteUserProfileById(@PathVariable("id") Integer id){
        userProfileService.deleteUserProfileById(id);
    }
}
