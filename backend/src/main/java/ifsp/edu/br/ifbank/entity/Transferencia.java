package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long id;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "data_transf", nullable = false)
    private LocalDateTime dataTransferencia;

    @Column(length = 255)
    private String descricao;

    @Column(length = 20, nullable = false)
    private String situacao;

    @ManyToOne
    @JoinColumn(name = "id_conta_origem", nullable = false)
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_conta_destino", nullable = false)
    private Conta contaDestino;

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public LocalDateTime getData_transf() {
        return dataTransferencia;
    }

    public void setData_transf(LocalDateTime data_transf) {
        this.dataTransferencia = data_transf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}