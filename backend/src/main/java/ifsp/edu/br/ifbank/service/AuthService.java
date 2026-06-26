package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
import ifsp.edu.br.ifbank.dto.LoginRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.entity.Perfil;
import ifsp.edu.br.ifbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Login
    public Cliente login(LoginRequest request) {

        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado."));

        if (!cliente.isAprovado()) {
            throw new RuntimeException("Conta ainda não aprovada pelo gerente.");
        }

        if (!cliente.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }

        return cliente;
    }

    // Cadastro
    public Cliente cadastrar(CadastroRequest request) {

        Cliente cliente = new Cliente();

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setEndereco(request.getEndereco());
        cliente.setSenha(request.getSenha());

        // Todo cliente novo começa como CLIENTE e aguardando aprovação
        cliente.setPerfil(Perfil.CLIENTE);
        cliente.setAprovado(false);

        return clienteRepository.save(cliente);
    }
}