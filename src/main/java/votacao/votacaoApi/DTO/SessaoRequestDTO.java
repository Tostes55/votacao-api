package votacao.votacaoApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SessaoRequestDTO {
    private Long idSessao;
    private Date dataInicioSessao;
    private Date dataFimSessao;
    private String resultadoFinal;
}
