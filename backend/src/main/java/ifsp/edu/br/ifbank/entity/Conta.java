package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;

    @Column (length = 10, nullable = false, unique = true)
    private String numeroConta;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column (length = 10, nullable = false)
    private String status; // PENDENTE, ATIVA, BLOQUEADA

    @Column (nullable = false)
    private LocalDateTime data_abertura;


    @OneToMany(mappedBy = "conta")
    private List<Movimentacao> movimentacoes;

    @OneToMany(mappedBy = "contaOrigem")
    private List<Transferencia> transferenciasEnviadas;

    @OneToMany(mappedBy = "contaDestino")
    private List<Transferencia> transferenciasRecebidas;

    @OneToMany(mappedBy = "conta")
    private List<Investimento> investimentos;


    @ManyToOne
    @JoinColumn(name = "id_usuario_gerente", nullable = false)
    private Gerente gerente;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public LocalDateTime getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(LocalDateTime data_abertura) {
        this.data_abertura = data_abertura;
    }
}