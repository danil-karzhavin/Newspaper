package karzhavin.newspaper.Exception.comment;

public class CommentDtoException extends RuntimeException{
    public CommentDtoException(String message){
        super(message);
    }
    public CommentDtoException(){
        super();
    }
}
