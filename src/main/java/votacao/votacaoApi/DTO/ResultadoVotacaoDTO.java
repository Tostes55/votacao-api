package votacao.votacaoApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDTO {
    private Long idPauta;
    private String tituloPauta;
    private long votosSim;
    private long votosNao;
    private long totalVotos;
    private String resultado;
}
