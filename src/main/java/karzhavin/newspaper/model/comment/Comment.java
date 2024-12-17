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

    @Column(name = "user_id", nullable = false)
    Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    User author;

    @Column(name = "news_item_id", nullable = false)
    Integer newsItemId;

    @ManyToOne
    @JoinColumn(name = "news_item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    News news;

    public Comment(){}

    public Comment(Integer id, String text, Integer userId, User author, Integer newsItemId, News news) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.author = author;
        this.newsItemId = newsItemId;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getUserId() {
        return userId;
    }

    public User getAuthor() {
        return author;
    }

    public Integer getNewsItemId() {
        return newsItemId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setNewsItemId(Integer newsItemId) {
        this.newsItemId = newsItemId;
    }

    public void setNews(News news) {
        this.news = news;
    }
}