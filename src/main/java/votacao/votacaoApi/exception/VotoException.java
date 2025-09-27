package votacao.votacaoApi.exception;

import votacao.votacaoApi.Enum.MensagemErro;

public class VotoException extends RuntimeException{

        private final MensagemErro mensagemErro;

        public VotoException(MensagemErro mensagemErro) {
            super(mensagemErro.getMensagem());
            this.mensagemErro = mensagemErro;
        }

        public VotoException(MensagemErro mensagemErro, String detalhes) {
            super(mensagemErro.getMensagem() + ": " + detalhes);
            this.mensagemErro = mensagemErro;
        }

        public MensagemErro getMensagemErro() {
            return mensagemErro;
        }
    }

