package andrianopasquale97.U5W2D3.exceptions;

import andrianopasquale97.U5W2D3.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExeceptionHandler {
  @ExceptionHandler(BadRequestExcepition.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handleBadRequestExcepition(BadRequestExcepition ex) {
    if(ex.getErrorsList() != null) {

      String message = ex.getErrorsList().stream()
              .map(objectError -> objectError.getDefaultMessage())
              .collect(Collectors.joining(". " ));
      return new ErrorsResponseDTO(message, LocalDateTime.now());

    } else {

      return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }
  }

  @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorsResponseDTO handleNotFound(NotFoundException ex){
    return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
  }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponseDTO handleGenericErrors(Exception ex){
      ex.printStackTrace();
      return new ErrorsResponseDTO("Problema lato server!!", LocalDateTime.now());
    }


}
