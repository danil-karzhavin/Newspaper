package karzhavin.newspaper.Exception.News;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(String message){
        super(message);
    }
}