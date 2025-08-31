package votacao.votacaoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import votacao.votacaoApi.model.Sessao;
import votacao.votacaoApi.model.Voto;

@Repository
    public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
