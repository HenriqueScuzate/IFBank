package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.DepositoRequest;
import ifsp.edu.br.ifbank.entity.Conta;
import ifsp.edu.br.ifbank.entity.Movimentacao;
import ifsp.edu.br.ifbank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
@CrossOrigin("*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarConta(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Conta> buscarContaPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(contaService.buscarPorClienteId(clienteId));
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Double> saldo(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.consultarSaldo(id));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody DepositoRequest request) {
        return ResponseEntity.ok(contaService.depositar(id, request.getValor()));
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<List<Movimentacao>> extrato(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.extrato(id));
    }

    @GetMapping("/{id}/extrato/paginado")
    public ResponseEntity<Page<Movimentacao>> extratoPaginado(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(contaService.extratoPaginado(id, pageable));
    }
}
