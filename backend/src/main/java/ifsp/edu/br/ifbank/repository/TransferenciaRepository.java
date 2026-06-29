package ifsp.edu.br.ifbank.repository;

import ifsp.edu.br.ifbank.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
