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
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo tipoVoto deve ser SIM ou NAO")
    private TipoVoto voto;
    /** Identificador da pauta em que o associado está votando. */
    private Long idPauta;
    private String cpfAssociado;
    private LocalDateTime dataVoto;


}
