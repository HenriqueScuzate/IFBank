package ifsp.edu.br.ifbank.dto;

public record CadastroRequest(

        String nome,
        String cpf,
        String email,
        String senha,
        String telefone,
        Integer numero_res,
        String fotoUrl,
        String role

) {
}