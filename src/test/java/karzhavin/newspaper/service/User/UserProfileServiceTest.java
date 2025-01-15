package karzhavin.newspaper.service.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import karzhavin.newspaper.model.user.UserProfile;
import karzhavin.newspaper.model.user.UserProfileDto;
import karzhavin.newspaper.repository.user.IUserProfileRepository;
import karzhavin.newspaper.service.user.IUserService;
import karzhavin.newspaper.service.user.UserProfileService;
import karzhavin.newspaper.Exception.user.UserProfile.UserProfileNotFoundException;
import karzhavin.newspaper.Exception.user.UserProfile.UserProfileDtoException;

public class UserProfileServiceTest {

    @Mock
    private IUserProfileRepository userProfileRepository;

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserProfileService userProfileService; // Тестируемый класс

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация mock объектов
    }

    @Test
    public void testGetUserProfileDtoByUserId_WhenProfileExists() {
        Integer userId = 1;
        UserProfile mockProfile = new UserProfile();
        mockProfile.setId(1);
        mockProfile.setUserId(userId);

        when(userProfileRepository.findByUserId(userId)).thenReturn(Optional.of(mockProfile));
        when(userProfileRepository.findById(mockProfile.getId())).thenReturn(Optional.of(mockProfile));

        UserProfileDto result = userProfileService.getUserProfileDtoByUserId(userId);

        assertNotNull(result);
        assertEquals(mockProfile.getId(), result.getId());
        verify(userProfileRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetUserProfileDtoByUserId_WhenProfileDoesNotExist() {
        Integer userId = 1;
        when(userProfileRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(UserProfileNotFoundException.class, () -> userProfileService.getUserProfileDtoByUserId(userId));
    }

    @Test
    public void testGetUserProfileById_WhenProfileExists() {
        Integer profileId = 1;
        UserProfile mockProfile = new UserProfile();
        mockProfile.setId(profileId);

        when(userProfileRepository.findById(profileId)).thenReturn(Optional.of(mockProfile));

        UserProfile result = userProfileService.getUserProfileById(profileId);

        assertNotNull(result);
        assertEquals(profileId, result.getId());
        verify(userProfileRepository, times(1)).findById(profileId);
    }

    @Test
    public void testGetUserProfileById_WhenProfileDoesNotExist() {
        Integer profileId = 1;
        when(userProfileRepository.findById(profileId)).thenReturn(Optional.empty());

        assertThrows(UserProfileNotFoundException.class, () -> userProfileService.getUserProfileById(profileId));
    }

    @Test
    public void testCreateUserProfile_WhenUserIdIsNull() {
        UserProfileDto inputProfileDto = new UserProfileDto();
        inputProfileDto.setUserId(null);

        assertThrows(UserProfileDtoException.class, () -> userProfileService.createUserProfile(inputProfileDto));
    }

    @Test
    public void testUpdateUserProfileById() {
        UserProfileDto inputProfileDto = new UserProfileDto();
        inputProfileDto.setId(1);
        inputProfileDto.setUserId(2);

        UserProfile existingProfile = new UserProfile();
        existingProfile.setId(1);

        when(userProfileRepository.findById(inputProfileDto.getId())).thenReturn(Optional.of(existingProfile));
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(existingProfile);
        when(userProfileRepository.findById(existingProfile.getId())).thenReturn(Optional.of(existingProfile));

        UserProfileDto result = userProfileService.updateUserProfileById(inputProfileDto);

        assertNotNull(result);
        assertEquals(existingProfile.getId(), result.getId());
        verify(userProfileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testDeleteUserProfileById() {
        Integer profileId = 1;

        userProfileService.deleteUserProfileById(profileId);
        verify(userProfileRepository, times(1)).deleteById(profileId);
    }
}