package votacao.votacaoApi.DTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoDTO {

    private String voto;
    private String cpfAssociado;
    private LocalDateTime dataVoto;


}
