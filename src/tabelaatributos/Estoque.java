package tabelaatributos;

public class Estoque {

    private int id_mov;
    private int id_material;
    private String descricao;   // descrição do material (quando é entrada)
    private int quantidade;
    private int id_demanda;     // opcional: referência à demanda (saída)
    private String tipo_mov;    // "ENTRADA" ou "SAIDA"
    private String data_mov;    // data do movimento (AAAA-MM-DD)

    public int getId_mov() { return id_mov; }
    public void setId_mov(int id_mov) { this.id_mov = id_mov; }

    public int getId_material() { return id_material; }
    public void setId_material(int id_material) { this.id_material = id_material; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getId_demanda() { return id_demanda; }
    public void setId_demanda(int id_demanda) { this.id_demanda = id_demanda; }

    public String getTipo_mov() { return tipo_mov; }
    public void setTipo_mov(String tipo_mov) { this.tipo_mov = tipo_mov; }

    public String getData_mov() { return data_mov; }
    public void setData_mov(String data_mov) { this.data_mov = data_mov; }
}
