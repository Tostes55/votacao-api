package votacao.votacaoApi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Voto")

public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoto;
    private String voto;
    private String cpfAssociado;

    public Voto(){
    }

    public Voto(Long idVoto, String voto, String cpfAssociado) {
        this.idVoto = idVoto;
        this.voto = voto;
        this.cpfAssociado = cpfAssociado;
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


}
