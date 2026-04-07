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
        if (conectar == null) return false;
        String sql = "INSERT INTO estoque_mov (id_material, descricao, quantidade, tipo_mov, data_mov) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, e.getId_material());
            stmt.setString(2, e.getDescricao());
            stmt.setInt(3, e.getQuantidade());
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
        String sql = "SELECT * FROM estoque_mov";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setId_mov(rs.getInt("id_mov"));
                e.setId_material(rs.getInt("id_material"));
                e.setDescricao(rs.getString("descricao"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setTipo_mov(rs.getString("tipo_mov"));
                e.setData_mov(rs.getString("data_mov"));
                e.setId_demanda(0); // coluna não existe em estoque_mov
                lista.add(e);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar estoque: " + ex.getMessage());
        }
        return lista;
    }

    // BUSCAR POR ID
    public Estoque getEstoque(int id_mov) {
        if (conectar == null) return null;
        String sql = "SELECT * FROM estoque_mov WHERE id_mov = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id_mov);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Estoque e = new Estoque();
                e.setId_mov(rs.getInt("id_mov"));
                e.setId_material(rs.getInt("id_material"));
                e.setDescricao(rs.getString("descricao"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setTipo_mov(rs.getString("tipo_mov"));
                e.setData_mov(rs.getString("data_mov"));
                return e;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar estoque: " + ex.getMessage());
        }
        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Estoque e) {
        if (conectar == null) return false;
        String sql = "UPDATE estoque_mov SET id_material=?, descricao=?, quantidade=?, tipo_mov=?, data_mov=? WHERE id_mov=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, e.getId_material());
            stmt.setString(2, e.getDescricao());
            stmt.setInt(3, e.getQuantidade());
            stmt.setString(4, e.getTipo_mov());
            stmt.setString(5, e.getData_mov());
            stmt.setInt(6, e.getId_mov());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar estoque: " + ex.getMessage());
            return false;
        }
    }

    // DELETAR
    public boolean deletar(int id_mov) {
        if (conectar == null) return false;
        String sql = "DELETE FROM estoque_mov WHERE id_mov = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id_mov);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estoque: " + e.getMessage());
            return false;
        }
    }
}
