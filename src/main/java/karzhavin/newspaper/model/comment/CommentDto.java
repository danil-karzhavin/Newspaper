package karzhavin.newspaper.model.comment;

public class CommentDto {
    String text;

    CommentDto (String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
