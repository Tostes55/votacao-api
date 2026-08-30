package votacao.votacaoApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroValidacaoDTO {

    private String campo;
    private String mensagem;

}
