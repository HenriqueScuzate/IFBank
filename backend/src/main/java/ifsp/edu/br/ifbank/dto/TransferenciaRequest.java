package ifsp.edu.br.ifbank.dto;

public class TransferenciaRequest {
    private Long contaOrigemId;
    private String numeroContaDestino;
    private Double valor;

    public Long getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Long contaOrigemId) { this.contaOrigemId = contaOrigemId; }

    public String getNumeroContaDestino() { return numeroContaDestino; }
    public void setNumeroContaDestino(String numeroContaDestino) { this.numeroContaDestino = numeroContaDestino; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}