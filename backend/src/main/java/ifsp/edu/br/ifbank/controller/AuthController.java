package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
import ifsp.edu.br.ifbank.dto.LoginRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Cliente cliente = authService.login(request);

        return ResponseEntity.ok(cliente);
    }

    // Cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroRequest request) {

        Cliente cliente = authService.cadastrar(request);

        return ResponseEntity.ok(cliente);
    }
}