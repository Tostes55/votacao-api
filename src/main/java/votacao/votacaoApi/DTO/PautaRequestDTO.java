package votacao.votacaoApi.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import votacao.votacaoApi.Enum.StatusPauta;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PautaRequestDTO {

    private LocalDateTime inicioVotacao;
    private LocalDateTime fimVotacao;

    @Enumerated(EnumType.ORDINAL)
    private StatusPauta statusPauta;

    private String tituloPauta;
    private String descricaoPauta;
    private String categoriaPauta;
}