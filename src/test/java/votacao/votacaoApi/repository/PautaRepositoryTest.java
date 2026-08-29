package votacao.votacaoApi.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.Enum.StatusPauta;
import votacao.votacaoApi.model.Pauta;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class PautaRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PautaRepository pautaRepository;

    @Test
    @DisplayName("Deve Retornar que a pauta existe pelo Titulo")
    void existsByTituloPautaIsTrue() {

        String tituloPauta = "tituloPauta";

        PautaRequestDTO dto = new PautaRequestDTO();
         dto.setTituloPauta(tituloPauta);
         dto.setCategoriaPauta("Categoria Pauta");
         dto.setStatusPauta(StatusPauta.RASCUNHO);

         this.createPauta(dto);

        Boolean result = this.pautaRepository.existsByTituloPauta(tituloPauta);

        assertThat(result).isTrue();

    }

    @Test
    @DisplayName("Não deve Retornar que a pauta existe pelo Titulo")
    void existsByTituloPautaIsFalse() {

        String tituloPauta = "tituloPauta";


        Boolean result = this.pautaRepository.existsByTituloPauta(tituloPauta);

        assertThat(result).isFalse();

    }

    private Pauta createPauta(PautaRequestDTO pautaRequestDTO) {

        Pauta newPauta = new Pauta();
        newPauta.setTituloPauta(pautaRequestDTO.getTituloPauta());
        newPauta.setDescricaoPauta(pautaRequestDTO.getDescricaoPauta());
        newPauta.setCategoriaPauta(pautaRequestDTO.getCategoriaPauta());
        newPauta.setStatusPauta(pautaRequestDTO.getStatusPauta());
        newPauta.setInicioVotacao(pautaRequestDTO.getInicioVotacao());
        newPauta.setFimVotacao(pautaRequestDTO.getFimVotacao());

        this.entityManager.persist(newPauta);

        return newPauta;
    }
}