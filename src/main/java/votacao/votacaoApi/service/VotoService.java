package votacao.votacaoApi.service;

import org.springframework.web.bind.annotation.RequestParam;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {this.votoRepository = votoRepository;}

}
//public List<VotoDTO> buscarVotos(@RequestParam Integer id){}
