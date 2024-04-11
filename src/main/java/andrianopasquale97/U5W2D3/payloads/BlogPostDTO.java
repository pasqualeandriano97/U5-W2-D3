package andrianopasquale97.U5W2D3.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record BlogPostDTO(
        @NotEmpty(message = "Il campo categoria è obbligatorio")
        @Size(message = "La categoria deve essere di almeno 3 caratteri e massimo 15", min = 3, max = 15)
        String category,
         @NotEmpty(message = "Il campo titolo è obbligatorio")
         @Size(message = "Il titolo deve essere di almeno 3 caratteri e massimo 15", min = 3, max = 15)
         String title,
         String cover,
       @NotEmpty(message = "Il campo contenuto è obbligatorio")
        @Size(message = "Il contenuto deve essere di almeno 10 caratteri", min = 10)
       String content,
       @NotEmpty(message = "Il campo tempo di lettura è obbligatorio")
        double readingTime,
        @NotEmpty(message = "Il campo autore è obbligatorio")
        int authorId
) {
}
