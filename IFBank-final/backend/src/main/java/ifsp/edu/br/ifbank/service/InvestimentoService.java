package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.InvestimentoRequest;
import ifsp.edu.br.ifbank.entity.Conta;
import ifsp.edu.br.ifbank.entity.Investimento;
import ifsp.edu.br.ifbank.entity.Movimentacao;
import ifsp.edu.br.ifbank.repository.ContaRepository;
import ifsp.edu.br.ifbank.repository.InvestimentoRepository;
import ifsp.edu.br.ifbank.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Investimento aplicar(InvestimentoRequest request) {
        if (request.getValor() == null || request.getValor() <= 0) {
            throw new RuntimeException("Valor do investimento deve ser maior que zero.");
        }

        Conta conta = contaRepository.findById(request.getContaId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        if (conta.getSaldo() < request.getValor()) {
            throw new RuntimeException("Saldo insuficiente para investimento.");
        }

        conta.setSaldo(conta.getSaldo() - request.getValor());
        contaRepository.save(conta);

        Investimento investimento = new Investimento();
        investimento.setConta(conta);
        investimento.setTipo(request.getTipo());
        investimento.setValor(request.getValor());
        investimento.setRendimento(0.0);
        investimento.setData(LocalDateTime.now());
        investimentoRepository.save(investimento);

        Movimentacao mov = new Movimentacao();
        mov.setConta(conta);
        mov.setTipo("INVESTIMENTO");
        mov.setValor(-request.getValor());
        mov.setData(LocalDateTime.now());
        movimentacaoRepository.save(mov);

        return investimento;
    }

    @Transactional
    public Investimento resgatar(Long investimentoId) {
        Investimento investimento = investimentoRepository.findById(investimentoId)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado."));

        // Rendimento simples de 1%
        double rendimento = investimento.getValor() * 0.01;
        double totalResgate = investimento.getValor() + rendimento;

        investimento.setRendimento(rendimento);
        investimentoRepository.save(investimento);

        Conta conta = investimento.getConta();
        conta.setSaldo(conta.getSaldo() + totalResgate);
        contaRepository.save(conta);

        Movimentacao mov = new Movimentacao();
        mov.setConta(conta);
        mov.setTipo("RESGATE");
        mov.setValor(totalResgate);
        mov.setData(LocalDateTime.now());
        movimentacaoRepository.save(mov);

        investimentoRepository.delete(investimento);

        return investimento;
    }

    public List<Investimento> listarPorConta(Long contaId) {
        return investimentoRepository.findByContaId(contaId);
    }
}
