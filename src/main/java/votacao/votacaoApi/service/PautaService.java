package votacao.votacaoApi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import votacao.votacaoApi.DTO.AbrirSessaoVotacaoDTO;
import votacao.votacaoApi.DTO.PautaDTO;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.DTO.ResultadoVotacaoDTO;
import votacao.votacaoApi.Enum.MensagemErro;
import votacao.votacaoApi.Enum.StatusPauta;
import votacao.votacaoApi.Enum.TipoVoto;
import votacao.votacaoApi.exception.PautaException;
import votacao.votacaoApi.mappers.PautaMapper;
import votacao.votacaoApi.mappers.SessaoMapper;
import votacao.votacaoApi.mappers.VotoMapper;
import votacao.votacaoApi.model.Pauta;
import votacao.votacaoApi.repository.PautaRepository;
import votacao.votacaoApi.repository.VotoRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final PautaMapper pautaMapper;
    private final SessaoMapper sessaoMapper;
    private final VotoMapper votoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);


    public PautaDTO buscarPauta(Long idPauta) {
        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        return pautaMapper.toDto(pauta);
    }

    public List<PautaDTO> buscarTodasPautas() {
        List<Pauta> pautas = pautaRepository.findAll();

        return pautas.stream()
                .map(pautaMapper::toDto)
                .toList();
    }

    public Pauta cadastrarPauta(PautaRequestDTO pautaRequestDTO) {
        String titulo = pautaRequestDTO.getTituloPauta();
        if (titulo == null || titulo.isBlank()) {
            throw new PautaException(MensagemErro.DADOS_INVALIDOS, "Título da pauta é obrigatório");
        }
        if (pautaRepository.existsByTituloPauta(titulo.trim())) {
            throw new PautaException(MensagemErro.PAUTA_JA_EXISTE, "Já existe uma pauta com esse título");
        }

        Pauta pauta = pautaMapper.toEntity(pautaRequestDTO);

        return pautaRepository.save(pauta);
    }

    public void apagarPauta(Long idPauta) {
        pautaRepository.deleteById(idPauta);
    }

    public Pauta abrirSessaoVotacao(Long idPauta, AbrirSessaoVotacaoDTO dto) {
        int minutos = (dto!= null && dto.getDuracaoMinutos()>0 ? dto.getDuracaoMinutos():1);

        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new PautaException(MensagemErro.PAUTA_NAO_ENCONTRADA, "id: " + idPauta));

        LocalDateTime agora = LocalDateTime.now();
        if (pauta.getStatusPauta() == StatusPauta.EM_ANDAMENTO
                && pauta.getFimVotacao() != null
                && agora.isBefore(pauta.getFimVotacao())) {
            throw new PautaException(MensagemErro.SESSAO_JA_EM_ANDAMENTO);
        }

        if(pauta.getStatusPauta() == StatusPauta.CONCLUIDA){
            throw new PautaException(MensagemErro.PAUTA_JA_CONCLUIDA);
        }

        pauta.setInicioVotacao(agora);
        pauta.setFimVotacao(agora.plusMinutes(minutos));
        pauta.setStatusPauta(StatusPauta.EM_ANDAMENTO);

        Pauta salva = pautaRepository.save(pauta);
        LOGGER.info("Sessão de votação aberta para pauta {} até {}", idPauta, salva.getFimVotacao());
        return salva;
    }

    public Pauta fecharSessaoVotacao(Long idPauta) {
        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new PautaException(MensagemErro.PAUTA_NAO_ENCONTRADA, "id: " + idPauta));

        if (pauta.getStatusPauta() == StatusPauta.CONCLUIDA) {
            throw new PautaException(MensagemErro.PAUTA_JA_CONCLUIDA);
        }
        if (pauta.getStatusPauta() != StatusPauta.EM_ANDAMENTO) {
            throw new PautaException(MensagemErro.SESSAO_NAO_ESTA_EM_ANDAMENTO);
        }

        pauta.setStatusPauta(StatusPauta.CONCLUIDA);
        Pauta salva = pautaRepository.save(pauta);
        LOGGER.info("Sessão de votação encerrada (CONCLUIDA) para pauta {}", idPauta);
        return salva;
    }

    public ResultadoVotacaoDTO obterResultadoVotacao(Long idPauta) {
        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new PautaException(MensagemErro.PAUTA_NAO_ENCONTRADA, "id: " + idPauta));

        long votosSim = votoRepository.countByPauta_IdPautaAndVoto(idPauta, TipoVoto.SIM);
        long votosNao = votoRepository.countByPauta_IdPautaAndVoto(idPauta, TipoVoto.NAO);
        long total = votosSim + votosNao;

        String resultado;
        if (total == 0) {
            resultado = "SEM_VOTOS";
        } else if (votosSim > votosNao) {
            resultado = "APROVADO";
        } else if (votosNao > votosSim) {
            resultado = "REPROVADO";
        } else {
            resultado = "EMPATE";
        }

        return new ResultadoVotacaoDTO(
                pauta.getIdPauta(),
                pauta.getTituloPauta(),
                votosSim,
                votosNao,
                total,
                resultado);
    }
}