package skypro.java.margolina.mysecondproject.exceptions;

public class EmptyQuestionException extends RuntimeException{
    public EmptyQuestionException() {
    }

    public EmptyQuestionException(String message) {
        super(message);
    }
}
