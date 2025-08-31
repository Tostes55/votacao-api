package votacao.votacaoApi.service;

import org.springframework.web.bind.annotation.RequestParam;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.model.Voto;
import votacao.votacaoApi.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {this.votoRepository = votoRepository;}

}

public static final Logger log = LoggerFactory.getLogger(VotoService.class);

public List<VotoDTO> listarVotos(@RequestParam Integer id){}
