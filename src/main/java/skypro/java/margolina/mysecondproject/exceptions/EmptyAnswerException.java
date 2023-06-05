package skypro.java.margolina.mysecondproject.exceptions;

public class EmptyAnswerException extends RuntimeException{
    public EmptyAnswerException() {
    }

    public EmptyAnswerException(String message) {
        super(message);
    }
}
