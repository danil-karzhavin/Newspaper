package karzhavin.newspaper.Exception.News;

public class NewsException extends RuntimeException {
    public NewsException(String message){
        super(message);
    }
    public NewsException(){
        super("");
    }
}