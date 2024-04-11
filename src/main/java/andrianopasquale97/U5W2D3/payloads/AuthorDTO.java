package andrianopasquale97.U5W2D3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
        @NotEmpty(message = "Il campo nome è obbligatorio")
        @Size(message = "Il nome deve essere di almeno 3 caratteri e massimo 15", min = 3,max = 15)
        String name,
        @NotEmpty(message = "Il campo cognome è obbligatorio")
        @Size(message = "Il cognome deve essere di almeno 3 caratteri e massimo 15", min = 3,max = 15)
        String surname,
        @NotEmpty(message = "Il campo email è obbligatorio")
        @Email(message = "Email non valida")
        String email,
        @NotEmpty(message = "Il campo data di nascita è obbligatorio")
        String dateOfBirth,
        String avatar
) {
}
