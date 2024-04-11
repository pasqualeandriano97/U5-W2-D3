package andrianopasquale97.U5W2D3.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestExcepition extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestExcepition(String message){
        super(message);
    }

    public BadRequestExcepition(List<ObjectError> errorsList){
        super("Ci sono stati errori di validazione nel payload!");
        this.errorsList = errorsList;
    }
}
