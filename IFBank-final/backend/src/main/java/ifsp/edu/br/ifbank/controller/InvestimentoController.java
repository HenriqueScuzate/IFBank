package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.InvestimentoRequest;
import ifsp.edu.br.ifbank.entity.Investimento;
import ifsp.edu.br.ifbank.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos")
@CrossOrigin("*")
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    @PostMapping
    public ResponseEntity<Investimento> aplicar(@RequestBody InvestimentoRequest request) {
        return ResponseEntity.ok(investimentoService.aplicar(request));
    }

    @DeleteMapping("/resgatar/{id}")
    public ResponseEntity<Investimento> resgatar(@PathVariable Long id) {
        return ResponseEntity.ok(investimentoService.resgatar(id));
    }

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<Investimento>> listar(@PathVariable Long contaId) {
        return ResponseEntity.ok(investimentoService.listarPorConta(contaId));
    }
}
