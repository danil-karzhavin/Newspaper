package model.news;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String textData;

    @Lob
    byte[] image;

    LocalDate dateCreation;
    LocalDate dateChange;
    Integer countLikes;

    Integer authorId;
    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    User author;

    @OneToMany(mappedBy = "newsItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Comment> comments;

    public News(){}
    public News(Integer id, String title, String textData, byte[] image, LocalDate dateCreation, Integer countLikes, Integer authorId, User author, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.textData = textData;
        this.image = image;
        this.dateCreation = dateCreation;
        this.countLikes = countLikes;
        this.authorId = authorId;
        this.author = author;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTextData() {
        return textData;
    }

    public byte[] getImage() {
        return image;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Integer getCountLikes() {
        return countLikes;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public User getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setCountLikes(Integer countLikes) {
        this.countLikes = countLikes;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}