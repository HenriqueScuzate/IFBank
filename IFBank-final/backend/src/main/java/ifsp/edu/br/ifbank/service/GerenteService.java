package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.entity.Conta;
import ifsp.edu.br.ifbank.repository.ClienteRepository;
import ifsp.edu.br.ifbank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GerenteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    public Page<Cliente> listarPendentes(Pageable pageable) {
        return clienteRepository.findByAprovado(false, pageable);
    }

    public Page<Cliente> listarAprovados(Pageable pageable) {
        return clienteRepository.findByAprovado(true, pageable);
    }

    public Cliente aprovarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        cliente.setAprovado(true);
        clienteRepository.save(cliente);

        boolean jaPossuiConta = contaRepository.findByClienteId(id).isPresent();
        if (!jaPossuiConta) {
            Conta conta = new Conta();
            conta.setNumeroConta(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            conta.setSaldo(0.0);
            conta.setStatus("ATIVA");
            conta.setCliente(cliente);
            contaRepository.save(conta);
        }

        return cliente;
    }

    public void rejeitarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        clienteRepository.delete(cliente);
    }
}
