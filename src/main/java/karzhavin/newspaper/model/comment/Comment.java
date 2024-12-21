package karzhavin.newspaper.model.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String text;

    @Column(name = "author_id", nullable = false)
    Integer authorId;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    User author;

    @Column(name = "news_id", nullable = false)
    Integer newsId;

    @ManyToOne
    @JoinColumn(name = "news_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    News news;

    public Comment(){}

    public Comment(Integer id, String text, Integer authorId, User author, Integer newsId, News news) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.author = author;
        this.newsId = newsId;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public User getAuthor() {
        return author;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public News getNews() {
        return news;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public void setNews(News news) {
        this.news = news;
    }
}