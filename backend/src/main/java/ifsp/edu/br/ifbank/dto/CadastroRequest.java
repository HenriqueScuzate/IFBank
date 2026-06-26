package ifsp.edu.br.ifbank.dto;

<<<<<<< HEAD
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
=======
public class CadastroRequest {

    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
>>>>>>> origin/main
}