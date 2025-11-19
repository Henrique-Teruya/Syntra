package tabelaatributos;

/**
 *
 * @author henrique.tqueiroz
 */
public class Estoque {
    
    private int id_material;
    private int quantidade;
    private int id_demanda;

    public int getId_material() {
        return id_material;
    }
    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_demanda() {
        return id_demanda;
    }
    public void setId_demanda(int id_demanda) {
        this.id_demanda = id_demanda;
    }
}
