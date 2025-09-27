package votacao.votacaoApi.Enum;

public enum MensagemErro {


        VOTO_JA_EXISTE("Já existe um voto cadastrado para este associado"),
        ASSOCIADO_NAO_ENCONTRADO("Associado não encontrado"),
        VOTO_NAO_ENCONTRADO("Voto não encontrado"),
        DADOS_INVALIDOS("Dados inválidos fornecidos"),
        ERRO_INTERNO("Erro interno do servidor");

        private final String mensagem;

        MensagemErro(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem () {
            return mensagem;
        }

    }


