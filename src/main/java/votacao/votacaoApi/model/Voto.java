package votacao.votacaoApi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voto")

public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idVoto;

    private String cpfAssociado;
    private String voto;
    private LocalDateTime dataVoto;

    public Voto(){

    }

    public Voto(Long idVoto, String voto, String cpfAssociado, LocalDateTime dataVoto) {
        this.idVoto = idVoto;
        this.voto = voto;
        this.cpfAssociado = cpfAssociado;
        this.dataVoto = dataVoto;
    }

    public Long getIdVoto() {
        return idVoto;
    }

    public String getVoto() {
        return voto;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setIdVoto(Long idVoto) {
        this.idVoto = idVoto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public LocalDateTime getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(LocalDateTime dataVoto) {
        this.dataVoto = dataVoto;
    }
}
