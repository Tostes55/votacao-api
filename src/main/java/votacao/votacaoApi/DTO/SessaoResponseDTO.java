package votacao.votacaoApi.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import votacao.votacaoApi.Enum.StatusPauta;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessaoResponseDTO {

    private Long idPauta;

    @Enumerated(EnumType.STRING)
    private StatusPauta statusPauta;

    private LocalDateTime inicioVotacao;
    private LocalDateTime fimVotacao;
}
