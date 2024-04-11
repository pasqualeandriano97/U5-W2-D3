package andrianopasquale97.U5W2D3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsPayoad {
        public String message;
        public LocalDateTime timestamp;
}
