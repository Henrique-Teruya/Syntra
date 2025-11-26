package tabelaatributos;

public class Garantia {

    private int id_garantia;
    private int id_cliente;
    private int id_material;
    private int meses_garantia;
    private String data_compra;

    public int getId_garantia() {
        return id_garantia;
    }
    public void setId_garantia(int id_garantia) {
        this.id_garantia = id_garantia;
    }

    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_material() {
        return id_material;
    }
    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public int getMeses_garantia() {
        return meses_garantia;
    }
    public void setMeses_garantia(int meses_garantia) {
        this.meses_garantia = meses_garantia;
    }

    public String getData_compra() {
        return data_compra;
    }
    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }
}
