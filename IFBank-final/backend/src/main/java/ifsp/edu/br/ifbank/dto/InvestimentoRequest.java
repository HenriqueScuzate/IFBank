package ifsp.edu.br.ifbank.dto;

public class InvestimentoRequest {

    private Long contaId;
    private String tipo;
    private Double valor;

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
