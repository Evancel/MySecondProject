package skypro.java.margolina.mysecondproject.exceptions;

public class EmptyCollectionException extends RuntimeException{
    public EmptyCollectionException() {
    }

    public EmptyCollectionException(String message) {
        super(message);
    }
}
