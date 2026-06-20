package votacao.votacaoApi.model;

import jakarta.persistence.*;
import votacao.votacaoApi.Enum.TipoVoto;

import java.time.LocalDateTime;

@Entity
@Table(name = "voto")

public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idVoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    private String cpfAssociado;

    @Enumerated(EnumType.STRING)
    private TipoVoto voto;
    
    private LocalDateTime dataVoto;

    public Voto(){

    }

    public Voto(Long idVoto, Pauta pauta, TipoVoto voto, String cpfAssociado, LocalDateTime dataVoto) {
        this.idVoto = idVoto;
        this.pauta = pauta;
        this.voto = voto;
        this.cpfAssociado = cpfAssociado;
        this.dataVoto = dataVoto;
    }

    public Long getIdVoto() {
        return idVoto;
    }

    public TipoVoto getVoto() {
        return voto;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setIdVoto(Long idVoto) {
        this.idVoto = idVoto;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public void setVoto(TipoVoto voto) {
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
