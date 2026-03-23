package tabelaatributos;

public class Demandas {

    private int id;                
    private int id_cliente;        
    private String descricao;
    private String data_solicitacao;
    private String entregueSouN;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getData_solicitacao() {
        return data_solicitacao;
    }
    public void setData_solicitacao(String data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public String getEntregueSouN() {
        return entregueSouN;
    }
    public void setEntregueSouN(String entregueSouN) {
        this.entregueSouN = entregueSouN;
    }
}
