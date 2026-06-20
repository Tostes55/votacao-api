package votacao.votacaoApi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.Enum.MensagemErro;
import votacao.votacaoApi.Enum.StatusPauta;
import votacao.votacaoApi.Enum.TipoVoto;
import votacao.votacaoApi.exception.VotoException;
import votacao.votacaoApi.mappers.VotoMapper;
import votacao.votacaoApi.model.Pauta;
import votacao.votacaoApi.model.Voto;
import votacao.votacaoApi.repository.PautaRepository;
import votacao.votacaoApi.repository.VotoRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private final VotoMapper votoMapper;

    private static final Logger logger = LoggerFactory.getLogger(VotoService.class);

    public List<VotoDTO> buscarVotosPeloCpf(@RequestParam String cpfAssociado) {
        List<Voto> listaVotosEncontrados = votoRepository.findByCpfAssociado(cpfAssociado);

        return votoMapper.toVotoDTOs(listaVotosEncontrados);
    }

    public Voto cadastrarVoto(VotoDTO votoDTO) {
        if (votoDTO.getCpfAssociado() == null || votoDTO.getCpfAssociado().trim().isEmpty() || "string".equals(votoDTO.getCpfAssociado())) {
            throw new VotoException(MensagemErro.DADOS_INVALIDOS, "CPF ou Voto não informado");
        }
        if (votoDTO.getIdPauta() == null) {
            throw new VotoException(MensagemErro.ID_PAUTA_OBRIGATORIO);
        }
        if (votoDTO.getVoto() == null) {
            throw new VotoException(MensagemErro.DADOS_INVALIDOS, "Voto não informado");
        }
        if (votoDTO.getVoto() != TipoVoto.SIM && votoDTO.getVoto() != TipoVoto.NAO) {
            throw new VotoException(MensagemErro.DADOS_INVALIDOS, "Voto deve ser SIM ou NAO");
        }

        String cpfLimpo = votoDTO.getCpfAssociado().replaceAll("[^0-9]", "");

        Pauta pauta = pautaRepository.findById(votoDTO.getIdPauta())
                .orElseThrow(() -> new VotoException(MensagemErro.PAUTA_NAO_ENCONTRADA, "id: " + votoDTO.getIdPauta()));

        validarJanelaVotacao(pauta);

        if (votoRepository.existsByCpfAssociadoAndPauta_IdPauta(cpfLimpo, pauta.getIdPauta())) {
            throw new VotoException(MensagemErro.VOTO_JA_EXISTE, "CPF: " + cpfLimpo + " na pauta " + pauta.getIdPauta());
        }

        Voto voto = votoMapper.toEntity(votoDTO);

        voto.setCpfAssociado(cpfLimpo);
        voto.setPauta(pauta);
        if (voto.getDataVoto() == null) {
            voto.setDataVoto(LocalDateTime.now());
        }

        Voto votoSalvo = votoRepository.save(voto);

        logger.info("Voto cadastrado com sucesso! ID: {}, CPF: {}, pauta: {}",
                votoSalvo.getIdVoto(), votoSalvo.getCpfAssociado(), pauta.getIdPauta());
        return votoSalvo;
    }

    private void validarJanelaVotacao(Pauta pauta) {
        if (pauta.getStatusPauta() != StatusPauta.EM_ANDAMENTO) {
            throw new VotoException(MensagemErro.SESSAO_NAO_ABERTA);
        }
        if (pauta.getInicioVotacao() == null || pauta.getFimVotacao() == null) {
            throw new VotoException(MensagemErro.SESSAO_NAO_ABERTA);
        }
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(pauta.getInicioVotacao())) {
            throw new VotoException(MensagemErro.SESSAO_NAO_ABERTA);
        }
        if (agora.isAfter(pauta.getFimVotacao())) {
            throw new VotoException(MensagemErro.SESSAO_JA_ENCERRADA);
        }
    }

    public void apagarVoto(Long idVoto) {votoRepository.deleteById(idVoto);}


}
