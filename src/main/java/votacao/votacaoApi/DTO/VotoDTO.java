package votacao.votacaoApi.DTO;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import votacao.votacaoApi.Enum.TipoVoto;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoDTO {
    @NotNull(message = "O campo tipoVoto deve ser SIM ou NAO")
    private TipoVoto voto;
    private Long idPauta;
    private String cpfAssociado;
    private LocalDateTime dataVoto;


}
