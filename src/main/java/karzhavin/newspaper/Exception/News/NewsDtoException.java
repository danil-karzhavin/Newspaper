package karzhavin.newspaper.Exception.News;

public class NewsDtoException extends RuntimeException{
    public NewsDtoException(String message){
        super(message);
    }
    NewsDtoException(){
        super("");
    }
}
