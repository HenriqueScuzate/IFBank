package ifsp.edu.br.ifbank.repository;

import ifsp.edu.br.ifbank.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByClienteId(Long clienteId);
    Optional<Conta> findByNumeroConta(String numeroConta);
}