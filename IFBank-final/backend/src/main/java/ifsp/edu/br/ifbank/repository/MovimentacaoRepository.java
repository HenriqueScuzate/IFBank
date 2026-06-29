package ifsp.edu.br.ifbank.repository;

import ifsp.edu.br.ifbank.entity.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByContaIdOrderByDataDesc(Long contaId);
    Page<Movimentacao> findByContaId(Long contaId, Pageable pageable);
}
