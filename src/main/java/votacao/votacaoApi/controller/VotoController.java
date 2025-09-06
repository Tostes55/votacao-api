package votacao.votacaoApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import votacao.votacaoApi.model.Voto;
import votacao.votacaoApi.repository.VotoRepository;


@RestController
@RequestMapping(value="/votos")
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;


    @GetMapping(value="voto")
    public ResponseEntity<Voto> buscarVotosPeloId(@PathVariable String id){
        return ResponseEntity.ok(votoRepository.findById(id).get());
    }

    @RestController
    @RequestMapping("/api/test")
    public class TestController {
        @GetMapping
        public String hello() {
            return "Hello Swagger";
        }
    }
}