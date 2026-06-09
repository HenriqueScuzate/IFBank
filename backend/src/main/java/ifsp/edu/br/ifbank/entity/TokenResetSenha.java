package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TokenResetSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long id;

    @Column(length = 255, nullable = false, unique = true)
    private String token;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Column (nullable = false)
    private boolean utilizado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getData_expiracao() {
        return dataExpiracao;
    }

    public void setData_expiracao(LocalDateTime data_expiracao) {
        this.dataExpiracao = data_expiracao;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }
}
