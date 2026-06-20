package votacao.votacaoApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import votacao.votacaoApi.DTO.AbrirSessaoVotacaoDTO;
import votacao.votacaoApi.DTO.PautaDTO;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.DTO.ResultadoVotacaoDTO;
import votacao.votacaoApi.model.Pauta;
import votacao.votacaoApi.service.PautaService;

@RestController
@RequestMapping(value ="/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService; // Injetando apenas o service! O repository fica encapsulado lá dentro.

    @GetMapping(value = "/buscarPauta/{idPauta}")
    @Operation(description = "Busca pauta pelo ID")
    @ApiResponse(responseCode = "200", description = "Consulta efetuada com sucesso", content = {
            @Content(schema = @Schema(implementation = PautaDTO.class))})
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar a pauta")
    public ResponseEntity<PautaDTO> buscarPautaPeloId(@PathVariable Long idPauta){
        PautaDTO pautaDTO = pautaService.buscarPauta(idPauta);
        return ResponseEntity.ok(pautaDTO);
    }

    @PostMapping(value="/cadastrar")
    @Operation(description = "Cadastre uma pauta nova")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody PautaRequestDTO pautaRequestDTO){
        Pauta pautaCriada = pautaService.cadastrarPauta(pautaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaCriada);
    }

    @GetMapping("/{idPauta}/resultado")
    @Operation(description = "Apuração: totais de SIM/NAO e resultado da votação nesta pauta")
    @ApiResponse(responseCode = "200", description = "Resultado calculado")
    @ApiResponse(responseCode = "400", description = "Pauta não encontrada")
    public ResponseEntity<ResultadoVotacaoDTO> resultadoVotacao(@PathVariable Long idPauta) {
        return ResponseEntity.ok(pautaService.obterResultadoVotacao(idPauta));
    }

    @PostMapping("/{idPauta}/sessao/abrir")
    @Operation(description = "Abre a sessão de votação com duração em minutos (após isso novos votos são recusados)")
    @ApiResponse(responseCode = "200", description = "Sessão aberta")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou sessão já ativa")
    public ResponseEntity<Pauta> abrirSessaoVotacao(
            @PathVariable Long idPauta,
            @RequestBody AbrirSessaoVotacaoDTO body) {
        Pauta pauta = pautaService.abrirSessaoVotacao(idPauta, body);
        return ResponseEntity.ok(pauta);
    }

    @PostMapping("/{idPauta}/sessao/fechar")
    @Operation(description = "Encerra a votação e define a pauta como CONCLUIDA (antes do fim do prazo, se desejado)")
    @ApiResponse(responseCode = "200", description = "Sessão encerrada")
    @ApiResponse(responseCode = "400", description = "Pauta não encontrada ou sessão não está em andamento")
    public ResponseEntity<Pauta> fecharSessaoVotacao(@PathVariable Long idPauta) {
        return ResponseEntity.ok(pautaService.fecharSessaoVotacao(idPauta));
    }

    @DeleteMapping("/{idPauta}")
    public ResponseEntity<String> deletarPauta(@PathVariable Long idPauta) {
        pautaService.apagarPauta(idPauta);
        return new ResponseEntity<>("Pauta apagada com sucesso", HttpStatus.OK);
    }
}