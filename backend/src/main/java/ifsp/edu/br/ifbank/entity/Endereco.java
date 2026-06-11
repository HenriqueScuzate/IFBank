package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column (length = 100, nullable = false)
    private String nome;

    @Column (length = 100, nullable = false)
    private String bairro;

    @Column (length = 30, nullable = false)
    private String cidade;

    @Column (length = 30, nullable = false)
    private String estado;

    @Column (length = 8, nullable = false)
    private String cep;

    @OneToMany(mappedBy = "endereco")
    private List<Usuario> usuarios;

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
