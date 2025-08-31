package votacao.votacaoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import votacao.votacaoApi.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
