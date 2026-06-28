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
    private StatusPauta statusPauta;
    /** Preenchidos ao abrir a sessão de votação; definem a janela em que votos são aceitos. */
    private LocalDateTime inicioVotacao;
    private LocalDateTime fimVotacao;
    private String tituloPauta;
    private String descricaoPauta;
    private String categoriaPauta;


    public Pauta(PautaRequestDTO requestDTO) {
        this.statusPauta = requestDTO.getStatusPauta();
        this.inicioVotacao = requestDTO.getInicioVotacao();
        this.fimVotacao = requestDTO.getFimVotacao();
        this.tituloPauta = requestDTO.getTituloPauta();
        this.descricaoPauta = requestDTO.getDescricaoPauta();
        this.categoriaPauta = requestDTO.getCategoriaPauta();
    }

}
