package karzhavin.newspaper.controller.user;

import karzhavin.newspaper.model.authenticate.AuthenticationRequest;
import karzhavin.newspaper.model.authenticate.AuthenticationResponse;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.security.JwtUtil;
import karzhavin.newspaper.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;
    JwtUtil jwtUtil;

    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
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
    @GetMapping("/dev/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/byEmail/")
    ResponseEntity<UserDto> getUserDtoByEmail(@RequestBody Map<String, Object> data){
        return ResponseEntity.ok(userService.getUserDtoByEmail(data));
    }

    @GetMapping("/byUserProfile/{userProfileId}")
    ResponseEntity<UserDto> getUserByUserProfileId(@PathVariable("userProfileId") Integer userProfileId){
        return ResponseEntity.ok(userService.getUserByUserDtoProfileId(userProfileId));
    }

    @GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUsersDto(){
        return ResponseEntity.ok(userService.getAllUsersDto());
    }

    @DeleteMapping("/")
    void deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final User user = userService.identicateAndAuthenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
