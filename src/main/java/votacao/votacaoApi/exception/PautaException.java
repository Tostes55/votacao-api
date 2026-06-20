package votacao.votacaoApi.exception;

import votacao.votacaoApi.Enum.MensagemErro;

public class PautaException extends RuntimeException {

        private final MensagemErro mensagemErro;

        public PautaException (MensagemErro mensagemErro){
            super(mensagemErro.getMensagem());
            this.mensagemErro = mensagemErro;
        }



    public PautaException(MensagemErro mensagemErro, String detalhes) {
        super(mensagemErro.getMensagem()+ ": " + detalhes);
        this.mensagemErro = mensagemErro;
    }
    public MensagemErro getMensagemErro() {return mensagemErro;}
}
