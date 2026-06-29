package ifsp.edu.br.ifbank.repository;

import ifsp.edu.br.ifbank.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Cliente> findByAprovado(boolean aprovado, Pageable pageable);
}
