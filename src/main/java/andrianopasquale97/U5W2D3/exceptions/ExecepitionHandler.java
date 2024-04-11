package andrianopasquale97.U5W2D3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExecepitionHandler {
  @ExceptionHandler(BadRequestExcepition.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayoad handleBadRequestExcepition(BadRequestExcepition ex) {
      return new ErrorsPayoad(ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayoad handleNotFoundException(NotFoundException ex) {
      return new ErrorsPayoad(ex.getMessage(), LocalDateTime.now());
  }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayoad handleAllExceptions(Exception ex) {
        return new ErrorsPayoad(ex.getMessage(), LocalDateTime.now());
    }


}
