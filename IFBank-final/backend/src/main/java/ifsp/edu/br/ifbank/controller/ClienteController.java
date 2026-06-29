package ifsp.edu.br.ifbank.controller;

import ifsp.edu.br.ifbank.dto.AtualizarClienteRequest;
import ifsp.edu.br.ifbank.entity.Cliente;
import ifsp.edu.br.ifbank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,
                                              @RequestBody AtualizarClienteRequest request) {
        return ResponseEntity.ok(clienteService.atualizar(id, request));
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<Cliente> uploadFoto(@PathVariable Long id,
                                              @RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        return ResponseEntity.ok(clienteService.uploadFoto(id, arquivo));
    }

    // Serve a imagem diretamente
    @GetMapping("/fotos/{nomeArquivo}")
    public ResponseEntity<Resource> getFoto(@PathVariable String nomeArquivo) {
        String caminho = System.getProperty("user.home") + File.separator + "ifbank-uploads" + File.separator + nomeArquivo;
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(arquivo);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
