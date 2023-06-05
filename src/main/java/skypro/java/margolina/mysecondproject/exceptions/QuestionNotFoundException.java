package skypro.java.margolina.mysecondproject.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
