package ifsp.edu.br.ifbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConta;
    private Double saldo;
    private String status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(mappedBy = "conta")
    @JsonIgnore
    private List<Movimentacao> movimentacoes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroConta() { return numeroConta; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Movimentacao> getMovimentacoes() { return movimentacoes; }
    public void setMovimentacoes(List<Movimentacao> movimentacoes) { this.movimentacoes = movimentacoes; }
}
