package ifsp.edu.br.ifbank.dto;

public class LoginResponse {

    private String mensagem;
    private ClienteDTO cliente;

    public LoginResponse() {}

    public LoginResponse(String mensagem, ClienteDTO cliente) {
        this.mensagem = mensagem;
        this.cliente = cliente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}