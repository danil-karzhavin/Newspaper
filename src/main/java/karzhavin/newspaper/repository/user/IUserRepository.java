package karzhavin.newspaper.repository.user;

import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.model.user.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserProfileId(Integer userProfileId);

    @Query("SELECT new karzhavin.newspaper.model.user.UserDto(u.id, u.username, u.password, u.email) FROM User u")
    List<UserDto> findAllUserDto();

    @Query("SELECT new karzhavin.newspaper.model.user.UserDto(u.id, u.username, u.password, u.email) " + "FROM User u WHERE u.email = :email")
    UserDto findUserDtoByEmail(@Param("email") String email);

    Optional<User> findByEmail(@Param("email") String email);

}