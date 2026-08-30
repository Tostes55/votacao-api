package votacao.votacaoApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import votacao.votacaoApi.DTO.*;
import votacao.votacaoApi.model.Pauta;
import votacao.votacaoApi.service.PautaService;

import java.util.List;

@RestController
@RequestMapping(value ="/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @GetMapping(value = "/buscarPauta/{idPauta}")
    @Operation(description = "Busca pauta pelo ID")
    @ApiResponse(responseCode = "200", description = "Consulta efetuada com sucesso", content = {
            @Content(schema = @Schema(implementation = PautaResponseDTO.class))})
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar a pauta")
    public ResponseEntity<PautaResponseDTO> buscarPautaPeloId(@Valid @PathVariable Long idPauta){
        PautaResponseDTO pautaEncontrada = pautaService.buscarPauta(idPauta);
        return ResponseEntity.ok(pautaEncontrada);
    }

    @GetMapping(value = "/buscarPauta")
    @Operation(description = "Busca todas as pautas")
    @ApiResponse(responseCode = "200", description = "Consulta efetuada com sucesso", content = {
            @Content(schema = @Schema(implementation = PautaDTO.class))})
    public ResponseEntity<List<PautaResponseDTO>> buscarTodasAsPautas(){
        List<PautaResponseDTO> pautas = this.pautaService.buscarTodasPautas();
        return ResponseEntity.ok(pautas);
    }


    @PostMapping(value="/cadastrar")
    @Operation(description = "Cadastre uma pauta nova")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<PautaResponseDTO> cadastrarPauta(@Valid @RequestBody PautaRequestDTO pautaRequestDTO){
        PautaResponseDTO pautaCriada = pautaService.cadastrarPauta(pautaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaCriada);
    }

    @GetMapping("/{idPauta}/resultado")
    @Operation(description = "Apuração: totais de SIM/NAO e resultado da votação nesta pauta")
    @ApiResponse(responseCode = "200", description = "Resultado calculado")
    @ApiResponse(responseCode = "400", description = "Pauta não encontrada")
    public ResponseEntity<ResultadoVotacaoDTO> resultadoVotacao(@Valid @PathVariable Long idPauta) {
        return ResponseEntity.ok(pautaService.obterResultadoVotacao(idPauta));
    }

    @PostMapping("/{idPauta}/sessao/abrir")
    @Operation(description = "Abre a sessão de votação com duração em minutos (após isso novos votos são recusados)")
    @ApiResponse(responseCode = "200", description = "Sessão aberta")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou sessão já ativa")
    public ResponseEntity<SessaoResponseDTO> abrirSessaoVotacao(
            @Valid
            @PathVariable Long idPauta,
            @RequestBody AbrirSessaoVotacaoDTO body) {
        SessaoResponseDTO sessaoVotacao = pautaService.abrirSessaoVotacao(idPauta, body);
        return ResponseEntity.ok(sessaoVotacao);
    }

    @PostMapping("/{idPauta}/sessao/fechar")
    @Operation(description = "Encerra a votação e define a pauta como CONCLUIDA (antes do fim do prazo, se desejado)")
    @ApiResponse(responseCode = "200", description = "Sessão encerrada")
    @ApiResponse(responseCode = "400", description = "Pauta não encontrada ou sessão não está em andamento")
    public ResponseEntity<SessaoResponseDTO> fecharSessaoVotacao(@Valid @PathVariable Long idPauta) {
        return ResponseEntity.ok(pautaService.fecharSessaoVotacao(idPauta));
    }

    @DeleteMapping("/{idPauta}")
    public ResponseEntity<String> deletarPauta(@Valid @PathVariable Long idPauta) {
        pautaService.apagarPauta(idPauta);
        return new ResponseEntity<>("Pauta apagada com sucesso", HttpStatus.OK);
    }
}