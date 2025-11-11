package votacao.votacaoApi.DTO;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import votacao.votacaoApi.Enum.TipoVoto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoDTO {
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo tipoVoto deve ser SIM ou NAO")
    private TipoVoto voto;
    private String cpfAssociado;
    private LocalDateTime dataVoto;


}
