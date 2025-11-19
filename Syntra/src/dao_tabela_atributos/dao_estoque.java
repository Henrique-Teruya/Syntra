package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexaomysql.Conexao;
import tabelaatributos.Estoque;

public class dao_estoque {
    private Conexao conexao;
    private Connection conectar;

    public dao_estoque() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }

    // Inserir estoque
    public void inserirDados(Estoque estoque) {
        String sql = "INSERT INTO estoque (quantidade, id_demanda) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getId_demanda());
            stmt.execute();
            stmt.close();
            System.out.println(" Estoque inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println(" Erro ao inserir estoque: " + e.getMessage());
        }
    }

    // Buscar estoque por ID
    public Estoque getEstoque(int id) {
        String sql = "SELECT * FROM estoque WHERE id_material = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setId_material(rs.getInt("id_material"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                estoque.setId_demanda(rs.getInt("id_demanda"));
                return estoque;
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao buscar estoque: " + e.getMessage());
        }
        return null;
    }
}
