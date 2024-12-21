package karzhavin.newspaper.model.user;

import java.time.LocalDate;

public class UserProfileDto {
    Integer id;
    String info;
    byte[] userImage;
    LocalDate dateCreation;
    Integer userId;

    public UserProfileDto(Integer id, String info, byte[] userImage, LocalDate dateCreation, Integer userId) {
        this.id = id;
        this.info = info;
        this.userImage = userImage;
        this.dateCreation = dateCreation;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Integer getUserId() {
        return userId;
    }
}
