package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.entity.Conta;
import ifsp.edu.br.ifbank.entity.Movimentacao;
import ifsp.edu.br.ifbank.repository.ContaRepository;
import ifsp.edu.br.ifbank.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));
    }

    public Conta buscarPorClienteId(Long clienteId) {
        return contaRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada para este cliente."));
    }

    public Double consultarSaldo(Long id) {
        return buscarPorId(id).getSaldo();
    }

    public List<Movimentacao> extrato(Long contaId) {
        buscarPorId(contaId);
        return movimentacaoRepository.findByContaIdOrderByDataDesc(contaId);
    }

    public Page<Movimentacao> extratoPaginado(Long contaId, Pageable pageable) {
        buscarPorId(contaId);
        return movimentacaoRepository.findByContaId(contaId, pageable);
    }

    @Transactional
    public Conta depositar(Long contaId, Double valor) {
        if (valor == null || valor <= 0) {
            throw new RuntimeException("Valor do depósito deve ser maior que zero.");
        }

        Conta conta = buscarPorId(contaId);
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        Movimentacao mov = new Movimentacao();
        mov.setConta(conta);
        mov.setTipo("DEPOSITO");
        mov.setValor(valor);
        mov.setData(LocalDateTime.now());
        movimentacaoRepository.save(mov);

        return conta;
    }
}
