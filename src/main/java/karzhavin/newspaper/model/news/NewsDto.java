package karzhavin.newspaper.model.news;

import java.time.LocalDate;

public class NewsDto {
    Integer id;
    String title;
    String textData;
    LocalDate dateCreation;
    byte[] image;
    Integer authorId;

    public NewsDto(Integer id, String title, String textData, LocalDate dateCreation, byte[] image, Integer authorId) {
        this.id = id;
        this.title = title;
        this.textData = textData;
        this.image = image;
        this.authorId = authorId;
        this.dateCreation = dateCreation;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
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

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
