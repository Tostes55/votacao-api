package votacao.votacaoApi.repository;

import votacao.votacaoApi.Enum.TipoVoto;
import votacao.votacaoApi.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface VotoRepository extends JpaRepository<Voto, Long> {

        List<Voto> findByCpfAssociado(String cpfAssociado);

    boolean existsByCpfAssociadoAndPauta_IdPauta(String cpfAssociado, Long idPauta);

    long countByPauta_IdPautaAndVoto(Long idPauta, TipoVoto voto);
}


