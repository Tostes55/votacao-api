package votacao.votacaoApi.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import votacao.votacaoApi.Enum.StatusPauta;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PautaRequestDTO {

    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime inicioVotacao;

    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fimVotacao;
    private StatusPauta statusPauta;

    @NotNull
    private String tituloPauta;

    @NotNull
    private String descricaoPauta;
    private String categoriaPauta;
}