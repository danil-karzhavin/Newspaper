package karzhavin.newspaper.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import karzhavin.newspaper.model.news.Comment;
import karzhavin.newspaper.model.news.News;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, unique = true)
    String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    UserProfile userProfile;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<News> news;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Comment> comments;

    public User(){}

    public User(Integer id, String username, String password, String email, UserProfile userProfile, List<News> news, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userProfile = userProfile;
        this.news = news;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<News> getNews() {
        return news;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
