package votacao.votacaoApi.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import votacao.votacaoApi.Enum.TipoVoto;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoDTO {
    @NotNull(message = "O campo tipoVoto deve ser SIM ou NAO")
    private TipoVoto voto;

    @NotNull
    private Long idPauta;
    
    @CPF(message = "Informe um CPF Válido")
    private String cpfAssociado;
    
    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataVoto;


}
