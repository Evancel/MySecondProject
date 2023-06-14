package skypro.java.margolina.mysecondproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class RepositoryNotExistsException extends RuntimeException{
    public RepositoryNotExistsException() {
    }

    public RepositoryNotExistsException(String message) {
        super(message);
    }
}
