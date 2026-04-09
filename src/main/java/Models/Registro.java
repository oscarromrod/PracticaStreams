package Models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class Registro {

    private LocalDateTime fechaHora;
    private Double temperatura;
    private Double humedad;

}
