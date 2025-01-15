package karzhavin.newspaper.service.User;

import karzhavin.newspaper.Exception.user.UserDtoException;
import karzhavin.newspaper.Exception.user.UserNotFoundException;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import karzhavin.newspaper.repository.user.IUserRepository;
import karzhavin.newspaper.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("password");
        userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
    }

    @Test
    void testIdenticateAndAuthenticate() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User authenticatedUser = userService.identicateAndAuthenticate("test@example.com", "password");

        assertNotNull(authenticatedUser);
        assertEquals("test@example.com", authenticatedUser.getEmail());
    }

    @Test
    void testIdenticateAndAuthenticate_UserNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.identicateAndAuthenticate("test@example.com", "password");
        });
    }

    @Test
    void testGetUserById_UserExists() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
    }

    @Test
    void testGetUserById_UserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1);
        });
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto);

        assertNotNull(updatedUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_IdIsNull() {
        userDto.setId(null);

        assertThrows(UserDtoException.class, () -> {
            userService.updateUser(userDto);
        });
    }

    @Test
    void testDeleteUserById() {
        userService.deleteUserById(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}