package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.TransferenciaRequest;
import ifsp.edu.br.ifbank.entity.Transferencia;
import ifsp.edu.br.ifbank.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin("*")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> realizar(@RequestBody TransferenciaRequest request) {
        return ResponseEntity.ok(transferenciaService.realizar(request));
    }
}
