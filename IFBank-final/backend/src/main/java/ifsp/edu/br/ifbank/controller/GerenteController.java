package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerente")
@CrossOrigin("*")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping("/clientes-pendentes")
    public ResponseEntity<Page<Cliente>> clientesPendentes(Pageable pageable) {
        return ResponseEntity.ok(gerenteService.listarPendentes(pageable));
    }

    @GetMapping("/clientes-aprovados")
    public ResponseEntity<Page<Cliente>> clientesAprovados(Pageable pageable) {
        return ResponseEntity.ok(gerenteService.listarAprovados(pageable));
    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity<Cliente> aprovar(@PathVariable Long id) {
        return ResponseEntity.ok(gerenteService.aprovarCliente(id));
    }

    @DeleteMapping("/rejeitar/{id}")
    public ResponseEntity<String> rejeitar(@PathVariable Long id) {
        gerenteService.rejeitarCliente(id);
        return ResponseEntity.ok("Cliente rejeitado.");
    }
}
