package votacao.votacaoApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.Enum.StatusPauta;

import java.time.LocalDateTime;

@Entity
@Table (name = "Pauta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPauta;

    @Enumerated(EnumType.STRING)
    private StatusPauta statusPauta;

    private LocalDateTime inicioVotacao;
    private LocalDateTime fimVotacao;
    private String tituloPauta;
    private String descricaoPauta;
    private String categoriaPauta;


}
