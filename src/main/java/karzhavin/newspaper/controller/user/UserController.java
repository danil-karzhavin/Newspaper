package karzhavin.newspaper.controller.user;

import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserDtoById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @PostMapping("/byEmail/")
    ResponseEntity<UserDto> getUserDtoByEmail(@RequestBody Map<String, Object> data){
        return ResponseEntity.ok(userService.getUserDtoByEmail(data));
    }

    @GetMapping("/byUserProfile/{userProfileId}")
    ResponseEntity<User> getUserByUserProfileId(@PathVariable("userProfileId") Integer userProfileId){
        return ResponseEntity.ok(userService.getUserByUserProfileId(userProfileId));
    }

    @GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUsersDto(){
        return ResponseEntity.ok(userService.getAllUsersDto());
    }

    @DeleteMapping("/")
    void deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
    }
}
