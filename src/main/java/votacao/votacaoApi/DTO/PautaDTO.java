package votacao.votacaoApi.DTO;
import lombok.*;
import votacao.votacaoApi.Enum.StatusPauta;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PautaDTO {

    private Long  idPauta;

    private StatusPauta statusPauta;
    private LocalDateTime inicioVotacao;
    private LocalDateTime fimVotacao;
    private String tituloPauta;
    private String descricaoPauta;
    private String categoriaPauta;


}