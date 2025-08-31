package votacao.votacaoApi.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaDTO {

    private Long idPauta;
    private String statusPauta;
    private String tituloPauta;
    private String descricaoPauta;


}