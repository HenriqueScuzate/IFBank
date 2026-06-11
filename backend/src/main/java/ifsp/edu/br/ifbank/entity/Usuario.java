package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column (length = 100, nullable = false)
    private String nome;

    @Column (length = 14, nullable = false, unique = true)
    private String cpf;

    @Column (length = 50, nullable = false)
    private String email;

    @Column (length = 255, nullable = false)
    private String senha;

    @Column (length = 20, nullable = false)
    private String telefone;

    @Column (nullable = false)
    private int numero_res;

    @Column (length = 255, nullable = false)
    private String fotoUrl; // upload depois

    @Column (length = 20, nullable = false)
    private String role;

    @Column (nullable = false)
    private LocalDateTime data_cadastro;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario")
    private List<TokenResetSenha> tokensResetSenha;

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumero_res() {
        return numero_res;
    }

    public void setNumero_res(int numero_res) {
        this.numero_res = numero_res;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<TokenResetSenha> getTokensResetSenha() {
        return tokensResetSenha;
    }

    public void setTokensResetSenha(List<TokenResetSenha> tokensResetSenha) {
        this.tokensResetSenha = tokensResetSenha;
    }
}