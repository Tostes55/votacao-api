package votacao.votacaoApi.Enum;

public enum StatusPauta {
    RASCUNHO("Rascunho"),
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em Andamento"),
    FECHADA("Fechada"),
    CONCLUIDA("Concluida");

    private final String statusPauta;

    StatusPauta(String statusPauta) {
        this.statusPauta = statusPauta;
    }
}
