package ifsp.edu.br.ifbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Double valor;
    private Double rendimento;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonIgnore
    private Conta conta;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Double getRendimento() { return rendimento; }
    public void setRendimento(Double rendimento) { this.rendimento = rendimento; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
}
