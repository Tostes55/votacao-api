package votacao.votacaoApi.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.service.VotoService;

import java.util.List;


@RestController
@RequestMapping(value="/votos")
public class VotoController {
    private final VotoService votoService;
    public VotoController(VotoService votoService) {
            this.votoService = votoService;

    }

    @GetMapping
    public ResponseEntity<List<VotoDTO>> listarVotos(@RequestParam Integer id){

        return null;
    }
}