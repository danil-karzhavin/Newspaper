package karzhavin.newspaper.model.comment;

public class CommentDto {
    Integer id;
    String text;
    Integer newsId;
    Integer authorId;

    public CommentDto(Integer id, String text, Integer newsId, Integer authorId) {
        this.id = id;
        this.text = text;
        this.newsId = newsId;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public Integer getAuthorId() {
        return authorId;
    }
}
