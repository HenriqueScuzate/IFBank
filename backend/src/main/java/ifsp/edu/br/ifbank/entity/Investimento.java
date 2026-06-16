package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_investimento")
    private Long id;

    @Column(length = 30, nullable = false)
    private String tipo;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valor_aplicado;

    @Column(nullable = false)
    private LocalDateTime data_aplicacao;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal rendimento;

    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor_aplicado;
    }

    public void setValor(BigDecimal valor) {
        this.valor_aplicado = valor;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public void setRendimento(BigDecimal rendimento) {
        this.rendimento = rendimento;
    }

    public LocalDateTime getData() {
        return data_aplicacao;
    }

    public void setData(LocalDateTime data) {
        this.data_aplicacao = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}