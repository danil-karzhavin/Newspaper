package model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String info;
    LocalDate dateCreation;

    @Lob
    byte[] userImage;

    Integer userId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference
    User user;

    public UserProfile(Integer id, String info, LocalDate dateCreation, byte[] userImage, Integer userId, User user) {
        this.id = id;
        this.info = info;
        this.dateCreation = dateCreation;
        this.userImage = userImage;
        this.userId = userId;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public Integer getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
