package votacao.votacaoApi.repository;

import votacao.votacaoApi.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
    public interface VotoRepository extends JpaRepository<Voto, Long> {

        Optional<Voto> findByCpfAssociado(String cpfAssociado);
    }


