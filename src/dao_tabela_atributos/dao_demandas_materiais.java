package dao_tabela_atributos;

import conexaomysql.Conexao;
import tabelaatributos.DemandasMateriais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dao_demandas_materiais {

    private Connection conectar;

    public dao_demandas_materiais() {
        this.conectar = new Conexao().getConexao();
    }

    public boolean inserirItem(DemandasMateriais dm) {
        String sql = "INSERT INTO demandas_materiais (id_demanda, id_material, quantidade_usada) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, dm.getId_demanda());
            stmt.setInt(2, dm.getId_material());
            stmt.setInt(3, dm.getQuantidade_usada());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir item de demanda: " + e.getMessage());
            return false;
        }
    }
}
