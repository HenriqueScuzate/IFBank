package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
import ifsp.edu.br.ifbank.dto.LoginRequest;
import ifsp.edu.br.ifbank.dto.LoginResponse;
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

    // LOGIN (corrigido IFB-36)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    // CADASTRO (ainda retorna Cliente por enquanto)
    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> cadastrar(@RequestBody CadastroRequest request) {

        Cliente cliente = authService.cadastrar(request);

        return ResponseEntity.ok(cliente);
    }
}