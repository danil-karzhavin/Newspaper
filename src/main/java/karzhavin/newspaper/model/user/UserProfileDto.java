package karzhavin.newspaper.model.user;

import java.time.LocalDate;

public class UserProfileDto {
    String info;
    byte[] userImage;
    LocalDate dateCreation;
    Integer userId;

    public UserProfileDto(String info, byte[] userImage, LocalDate dateCreation, Integer userId) {
        this.info = info;
        this.userImage = userImage;
        this.dateCreation = dateCreation;
        this.userId = userId;
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
