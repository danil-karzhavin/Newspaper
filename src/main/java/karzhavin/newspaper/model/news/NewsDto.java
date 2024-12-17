package karzhavin.newspaper.model.news;

public class NewsDto {
    Integer id;
    String title;
    String textData;
    byte[] image;
    Integer authorId;

    public NewsDto(Integer id, String title, String textData, byte[] image, Integer authorId) {
        this.id = id;
        this.title = title;
        this.textData = textData;
        this.image = image;
        this.authorId = authorId;
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
}
