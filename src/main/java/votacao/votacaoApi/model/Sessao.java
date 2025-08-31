package votacao.votacaoApi.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSessao;
    private Date  dataInicioSessao;
    private Date dataFimSessao;
    private String resultadoSessao;
    private int votosAFavor;
    private int votosContra;
    private String resultadoFinal;

    public Sessao(){
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public Date getDataInicioSessao() {
        return dataInicioSessao;
    }

    public void setDataInicioSessao(Date dataInicioSessao) {
        this.dataInicioSessao = dataInicioSessao;
    }

    public Date getDataFimSessao() {
        return dataFimSessao;
    }

    public void setDataFimSessao(Date dataFimSessao) {
        this.dataFimSessao = dataFimSessao;
    }

    public String getResultadoSessao() {
        return resultadoSessao;
    }

    public void setResultadoSessao(String resultadoSessao) {
        this.resultadoSessao = resultadoSessao;
    }

    public int getVotosAFavor() {
        return votosAFavor;
    }

    public void setVotosAFavor(int votosAFavor) {
        this.votosAFavor = votosAFavor;
    }

    public int getVotosContra() {
        return votosContra;
    }

    public void setVotosContra(int votosContra) {
        this.votosContra = votosContra;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }



}
