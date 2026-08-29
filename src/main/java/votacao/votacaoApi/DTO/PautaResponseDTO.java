package votacao.votacaoApi.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import votacao.votacaoApi.Enum.StatusPauta;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PautaResponseDTO {

    private Long idPauta;
    private StatusPauta statusPauta;
    private String tituloPauta;
    private String descricaoPauta;
    private String categoriaPauta;
}
