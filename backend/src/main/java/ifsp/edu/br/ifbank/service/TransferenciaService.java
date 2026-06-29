package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.TransferenciaRequest;
import ifsp.edu.br.ifbank.entity.Conta;
import ifsp.edu.br.ifbank.entity.Movimentacao;
import ifsp.edu.br.ifbank.entity.Transferencia;
import ifsp.edu.br.ifbank.repository.ContaRepository;
import ifsp.edu.br.ifbank.repository.MovimentacaoRepository;
import ifsp.edu.br.ifbank.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransferenciaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Transferencia realizar(TransferenciaRequest request) {
        if (request.getValor() == null || request.getValor() <= 0) {
            throw new RuntimeException("Valor deve ser maior que zero.");
        }

        Conta origem = contaRepository.findById(request.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

        Conta destino = contaRepository.findByNumeroConta(request.getNumeroContaDestino().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));

        if (origem.getId().equals(destino.getId())) {
            throw new RuntimeException("Não é possível transferir para a mesma conta.");
        }

        if (origem.getSaldo() < request.getValor()) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        origem.setSaldo(origem.getSaldo() - request.getValor());
        destino.setSaldo(destino.getSaldo() + request.getValor());
        contaRepository.save(origem);
        contaRepository.save(destino);

        Transferencia transferencia = new Transferencia();
        transferencia.setValor(request.getValor());
        transferencia.setData(LocalDateTime.now());
        transferencia.setContaOrigem(origem);
        transferencia.setContaDestino(destino);
        transferenciaRepository.save(transferencia);

        registrarMovimentacao(origem, "TRANSFERENCIA_ENVIADA", -request.getValor());
        registrarMovimentacao(destino, "TRANSFERENCIA_RECEBIDA", request.getValor());

        return transferencia;
    }

    private void registrarMovimentacao(Conta conta, String tipo, Double valor) {
        Movimentacao mov = new Movimentacao();
        mov.setConta(conta);
        mov.setTipo(tipo);
        mov.setValor(valor);
        mov.setData(LocalDateTime.now());
        movimentacaoRepository.save(mov);
    }
}