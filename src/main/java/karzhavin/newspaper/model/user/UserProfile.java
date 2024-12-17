package karzhavin.newspaper.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "info", length = 5000)
    String info;

    @Column(name = "date_creation", nullable = false)
    LocalDate dateCreation;

    @Lob
    @Column(name = "user_image")
    byte[] userImage;

    @Column(name = "user_id", nullable = false)
    Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    User user;

    public  UserProfile(){}

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
