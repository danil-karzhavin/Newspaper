package karzhavin.newspaper.model.user;

import java.time.LocalDate;

public class UserProfileDto {
    Integer id;
    String info;
    byte[] userImage;
    LocalDate dateCreation;
    Integer userId;

    public UserProfileDto(){}

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
