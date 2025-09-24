package votacao.votacaoApi.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoDTO {
    private Long idVoto;
    private String voto;
    private String cpfAssociado;



}
