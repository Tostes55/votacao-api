package votacao.votacaoApi.Enum;

public enum MensagemErro {


        VOTO_JA_EXISTE("Já existe um voto cadastrado para este associado"),
        ASSOCIADO_NAO_ENCONTRADO("Associado não encontrado"),
        VOTO_NAO_ENCONTRADO("Voto não encontrado"),
        DADOS_INVALIDOS("Dados inválidos fornecidos"),
        ERRO_INTERNO("Erro interno do servidor"),
        PAUTA_NAO_ENCONTRADA("Pauta não encontrada"),
        PAUTA_JA_EXISTE("Pauta já existente"),
        SESSAO_NAO_ABERTA("Não há sessão de votação aberta para esta pauta"),
        SESSAO_JA_ENCERRADA("O prazo para votar nesta pauta já encerrou"),
        SESSAO_JA_EM_ANDAMENTO("Já existe uma sessão de votação em andamento para esta pauta"),
        ID_PAUTA_OBRIGATORIO("É necessário informar a pauta do voto"),
        DURACAO_SESSAO_INVALIDA("A duração da sessão deve ser maior que zero"),
        SESSAO_NAO_ESTA_EM_ANDAMENTO("Não há sessão de votação em andamento para encerrar"),
        PAUTA_JA_CONCLUIDA("Esta pauta já está concluída");

        private final String mensagem;

        MensagemErro(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem () {
            return mensagem;
        }

    }


