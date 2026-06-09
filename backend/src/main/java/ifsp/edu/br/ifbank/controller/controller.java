@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PutMapping("/{id}")
    public Cliente atualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente dados) {

        cliente.setNome(dados.getNome());
        cliente.setEmail(dados.getEmail());
        cliente.setTelefone(dados.getTelefone());
        cliente.setEndereco(dados.getEndereco());

        if(dados.getSenha() != null &&
           !dados.getSenha().isBlank()) {
            cliente.setSenha(dados.getSenha());
        }

        return repository.save(cliente);
    }
}