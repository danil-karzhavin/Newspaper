package karzhavin.newspaper.controller.user;

import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.service.user.IUserService;
import karzhavin.newspaper.service.user.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PostMapping("/")
    ResponseEntity<User> updateUserById(Integer userId, @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @GetMapping("/")
    ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/")
    ResponseEntity<User> getUserByUserProfileId(@PathVariable("userProfileId") Integer userProfileId){
        return ResponseEntity.ok(userService.getUserByUserProfileId(userProfileId));
    }

    @DeleteMapping("/")
    void deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
    }
}
