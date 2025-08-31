package votacao.votacaoApi.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPauta;
    private String statusPauta;
    private String tituloPauta;
    private String descricaoPauta;

    public Pauta(){
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public String getStatusPauta() {
        return statusPauta;
    }

    public void setStatusPauta(String statusPauta) {
        this.statusPauta = statusPauta;
    }

    public String getTituloPauta() {
        return tituloPauta;
    }

    public void setTituloPauta(String tituloPauta) {
        this.tituloPauta = tituloPauta;
    }

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
    }




}
