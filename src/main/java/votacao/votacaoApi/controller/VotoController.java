package votacao.votacaoApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.model.Voto;
import votacao.votacaoApi.repository.VotoRepository;
import votacao.votacaoApi.service.VotoService;

import java.util.Optional;


@RestController
@RequestMapping(value = "/votos")
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private VotoService votoService;

    @GetMapping(value = "/buscarVoto/{cpfAssociado}")
    @Operation(description = "Buscar os votos pelo CPF do associado")
    @ApiResponse(responseCode = "200", description = "Consulta efetuada com sucesso", content = {
            @Content(schema = @Schema(implementation = VotoDTO.class))})
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar os votos")


    public Optional<Voto> buscarVotosPeloCpf(@PathVariable String cpfAssociado) {
        return this.votoRepository.findByCpfAssociado(cpfAssociado);
    }


    @PostMapping(value = "/cadastrar")
    @Operation(description = "Cadastra um novo voto")
    public ResponseEntity<String> cadastraVoto(@RequestBody VotoDTO votoDTO) {
        votoService.cadastrarVoto(votoDTO);

        return new ResponseEntity<>("Voto cadastrado com sucesso", HttpStatus.CREATED);
    }

    @DeleteMapping("/idVoto")
    public ResponseEntity<String> apagarVoto(@PathVariable Long idVoto) {
        votoService.apagarVoto(idVoto);

        return new ResponseEntity<>("Voto apagado com sucesso", HttpStatus.OK);
    }




/*    @RestController
    @RequestMapping("/api/test")
    public class TestController {
        @GetMapping
        public String hello() {
            return "Hello Swagger";
        }
    }*/
}