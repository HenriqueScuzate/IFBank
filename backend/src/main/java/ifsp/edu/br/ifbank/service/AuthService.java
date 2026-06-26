package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
import ifsp.edu.br.ifbank.dto.LoginRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.entity.Perfil;
import ifsp.edu.br.ifbank.repository.ClienteRepository;
import ifsp.edu.br.ifbank.dto.ClienteDTO;
import ifsp.edu.br.ifbank.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado."));

        // primeiro valida aprovação
        if (!cliente.isAprovado()) {
            throw new RuntimeException("Conta ainda não aprovada pelo gerente.");
        }

        // depois valida senha
        if (!cliente.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }

        // transforma entidade em DTO (NUNCA retornar entidade)
        ClienteDTO dto = new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getPerfil().name()
        );

        return new LoginResponse("Login realizado com sucesso.", dto);
    }

    // CADASTRO
    public Cliente cadastrar(CadastroRequest request) {

        //valida email duplicado (IFB-35)
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setEndereco(request.getEndereco());
        cliente.setSenha(request.getSenha());

        // padrão do sistema
        cliente.setPerfil(Perfil.CLIENTE);
        cliente.setAprovado(false);

        return clienteRepository.save(cliente);
    }
}