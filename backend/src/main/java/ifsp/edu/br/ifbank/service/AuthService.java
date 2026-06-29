package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
import ifsp.edu.br.ifbank.dto.LoginRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.entity.Perfil;
import ifsp.edu.br.ifbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    // LOGIN
    public Cliente login(LoginRequest request) {

        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado."));

        // Verifica se a conta foi aprovada
        if (!cliente.isAprovado()) {
            throw new RuntimeException("Conta ainda não aprovada pelo gerente.");
        }

        // Verifica a senha
        if (!cliente.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }

        return cliente;
    }

    // CADASTRO
    public Cliente cadastrar(CadastroRequest request) {

        // Verifica se o e-mail já está cadastrado
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

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

    // RECUPERAÇÃO DE SENHA
    public void recuperarSenha(String email) {

        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado."));

        // Gera uma senha temporária
        String novaSenha = UUID.randomUUID().toString().substring(0, 8);

        // Salva a nova senha
        cliente.setSenha(novaSenha);
        clienteRepository.save(cliente);

        // Envia a nova senha por e-mail
        emailService.enviarEmail(
                cliente.getEmail(),
                "Recuperação de senha - IFBank",
                "Olá, " + cliente.getNome() + "!\n\n"
                        + "Sua nova senha temporária é: " + novaSenha + "\n\n"
                        + "Faça login utilizando essa senha e altere-a assim que possível.\n\n"
                        + "Equipe IFBank"
        );
    }
}