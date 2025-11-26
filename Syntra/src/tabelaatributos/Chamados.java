package tabelaatributos;

public class Chamados {

    private int id_chamado;
    private int id_cliente;
    private String descricao;
    private String data_abertura;
    private String status;

    public int getId_chamado() {
        return id_chamado;
    }
    public void setId_chamado(int id_chamado) {
        this.id_chamado = id_chamado;
    }

    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_abertura() {
        return data_abertura;
    }
    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
