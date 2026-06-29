package ifsp.edu.br.ifbank.service;

import ifsp.edu.br.ifbank.dto.AtualizarClienteRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String PASTA_UPLOADS = System.getProperty("user.home") + File.separator + "ifbank-uploads" + File.separator;

    public Page<Cliente> listarTodos(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    public Cliente atualizar(Long id, AtualizarClienteRequest request) {
        Cliente cliente = buscarPorId(id);

        if (request.getNome() != null && !request.getNome().isBlank()) {
            cliente.setNome(request.getNome());
        }
        if (request.getTelefone() != null && !request.getTelefone().isBlank()) {
            cliente.setTelefone(request.getTelefone());
        }
        if (request.getEndereco() != null && !request.getEndereco().isBlank()) {
            cliente.setEndereco(request.getEndereco());
        }
        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            cliente.setSenha(request.getSenha());
        }

        return clienteRepository.save(cliente);
    }

    public Cliente uploadFoto(Long clienteId, MultipartFile arquivo) throws IOException {
        Cliente cliente = buscarPorId(clienteId);

        Path pasta = Paths.get(PASTA_UPLOADS);
        if (!Files.exists(pasta)) {
            Files.createDirectories(pasta);
        }

        String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path destino = pasta.resolve(nomeArquivo);
        arquivo.transferTo(destino.toFile());

        // Salva só o nome do arquivo, não o caminho completo
        cliente.setFotoUrl("/fotos/" + nomeArquivo);
        return clienteRepository.save(cliente);
    }
}
