package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.CadastroRequest;
<<<<<<< HEAD
import ifsp.edu.br.ifbank.entity.Usuario;
import ifsp.edu.br.ifbank.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(CadastroRequest request) {

        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }

        if (usuarioRepository.findByCpf(request.cpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setCpf(request.cpf());
        usuario.setEmail(request.email());

        // Criptografa a senha
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        usuario.setTelefone(request.telefone());
        usuario.setNumero_res(request.numero_res());
        usuario.setFotoUrl(request.fotoUrl());
        usuario.setRole(request.role());

        usuario.setData_cadastro(LocalDateTime.now());

        return usuarioRepository.save(usuario);
=======
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
>>>>>>> origin/main
    }
}