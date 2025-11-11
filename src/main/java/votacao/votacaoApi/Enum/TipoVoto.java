package votacao.votacaoApi.Enum;

public enum TipoVoto {
    SIM ("SIM"),
    NAO ("NAO");

    private final String Voto;

    TipoVoto(String Voto) {
        this.Voto = Voto;
    }
}
