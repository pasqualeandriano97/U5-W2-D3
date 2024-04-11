package andrianopasquale97.U5W2D3.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(
        String message,
        LocalDateTime timestamp

) {
}
