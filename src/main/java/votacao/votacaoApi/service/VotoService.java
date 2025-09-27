package votacao.votacaoApi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.Enum.MensagemErro;
import votacao.votacaoApi.exception.VotoException;
import votacao.votacaoApi.model.Voto;
import votacao.votacaoApi.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VotoService {

    private final VotoRepository votoRepository;

    private static final Logger logger = LoggerFactory.getLogger(VotoService.class);

    public VotoService(VotoRepository votoRepository) {this.votoRepository = votoRepository;}

    public List<VotoDTO> buscarVotosPeloCpf(@RequestParam String cpfAssociado) {
        List<Voto> listaVotosEncontrados = votoRepository.findAll();
        return converterListaVotosParaListaVotosDTO(listaVotosEncontrados);
    }

    public Voto cadastrarVoto(VotoDTO votoDTO) {
        // Validação básica do CPF
        if (votoDTO.getCpfAssociado() == null || votoDTO.getCpfAssociado().trim().isEmpty()) {
            throw new VotoException(MensagemErro.DADOS_INVALIDOS, "CPF não informado");
        }

        String cpfLimpo = votoDTO.getCpfAssociado().replaceAll("[^0-9]", "");

        if (votoRepository.existsByCpfAssociado(cpfLimpo)) {
            throw new VotoException(MensagemErro.VOTO_JA_EXISTE, "CPF: " + cpfLimpo);
        }

        Voto voto = converterVotoDTOParaVoto(votoDTO);
        voto.setCpfAssociado(cpfLimpo);

        Voto votoSalvo = votoRepository.save(voto);

        logger.info("Voto cadastrado com sucesso! ID: {}, CPF: {}",
                votoSalvo.getIdVoto(), votoSalvo.getCpfAssociado());

        return votoSalvo;
    }

    public void apagarVoto(Long idVoto) {votoRepository.deleteById(idVoto);}

    public VotoDTO converterVotoParaVotoDTO(Voto voto) {
        VotoDTO votoDTO = new VotoDTO();

        votoDTO.setDataVoto(voto.getDataVoto());
        votoDTO.setVoto(voto.getVoto());
        votoDTO.setCpfAssociado(voto.getCpfAssociado());

        return votoDTO;
    }

    public List<VotoDTO> converterListaVotosParaListaVotosDTO(List<Voto> listaVotos) {
        return listaVotos.stream().map(this::converterVotoParaVotoDTO).collect(Collectors.toList());
    }

    public Voto converterVotoDTOParaVoto(VotoDTO votoDTO) {
        Voto voto = new Voto();

        voto.setVoto(votoDTO.getVoto());
        voto.setDataVoto(votoDTO.getDataVoto());
        voto.setCpfAssociado(votoDTO.getCpfAssociado());

        return voto;
    }

}
//public List<VotoDTO> buscarVotos(@RequestParam Integer id){}
