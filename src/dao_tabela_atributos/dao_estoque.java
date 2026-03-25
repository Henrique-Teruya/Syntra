package dao_tabela_atributos;

import conexaomysql.Conexao;
import tabelaatributos.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dao_estoque {

    private Connection conectar;

    public dao_estoque() {
        this.conectar = new Conexao().getConexao();
    }

    // INSERIR MOVIMENTO
    public boolean inserirDados(Estoque e) {
        String sql = "INSERT INTO estoque (descricao, quantidade, id_demanda, tipo_mov, data_mov) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, e.getDescricao());
            stmt.setInt(2, e.getQuantidade());
            if (e.getId_demanda() > 0) stmt.setInt(3, e.getId_demanda()); else stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setString(4, e.getTipo_mov());
            stmt.setString(5, e.getData_mov());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir movimento de estoque: " + ex.getMessage());
            return false;
        }
    }

    // LISTAR TODOS
    public List<Estoque> listarTodos() {
        List<Estoque> lista = new ArrayList<>();
        if (conectar == null) return lista;
        String sql = "SELECT * FROM estoque";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setId_material(rs.getInt("id_material"));
                e.setDescricao(rs.getString("descricao"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setId_demanda(rs.getInt("id_demanda"));
                e.setTipo_mov(rs.getString("tipo_mov"));
                e.setData_mov(rs.getString("data_mov"));
                lista.add(e);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar estoque: " + ex.getMessage());
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizar(Estoque e) {
        String sql = "UPDATE estoque SET descricao=?, quantidade=?, id_demanda=?, tipo_mov=?, data_mov=? WHERE id_material=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, e.getDescricao());
            stmt.setInt(2, e.getQuantidade());
            if (e.getId_demanda() > 0) stmt.setInt(3, e.getId_demanda()); else stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setString(4, e.getTipo_mov());
            stmt.setString(5, e.getData_mov());
            stmt.setInt(6, e.getId_material());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar estoque: " + ex.getMessage());
            return false;
        }
    }

    // DELETAR
    public boolean deletar(int id) {
        String sql = "DELETE FROM estoque WHERE id_material = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estoque: " + e.getMessage());
            return false;
        }
    }
}
