package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaomysql.Conexao;
import tabelaatributos.Garantia;

public class dao_garantia {

    private Connection conectar;

    public dao_garantia() {
        this.conectar = new Conexao().getConexao();
    }

    // INSERIR GARANTIA
    public boolean inserirDados(Garantia g) {
        String sql = "INSERT INTO garantia (id_cliente, id_material, id_demanda, meses_garantia, data_compra) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, g.getId_cliente());
            stmt.setInt(2, g.getId_material());
            if (g.getId_demanda() > 0) stmt.setInt(3, g.getId_demanda()); else stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setInt(4, g.getMeses_garantia());
            stmt.setString(5, g.getData_compra());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir garantia: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS
    public List<Garantia> listarTodos() {
        List<Garantia> lista = new ArrayList<>();
        if (conectar == null) return lista;
        String sql = "SELECT * FROM garantia";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Garantia g = new Garantia();
                g.setId_garantia(rs.getInt("id_garantia"));
                g.setId_cliente(rs.getInt("id_cliente"));
                g.setId_material(rs.getInt("id_material"));
                g.setId_demanda(rs.getInt("id_demanda"));
                g.setMeses_garantia(rs.getInt("meses_garantia"));
                g.setData_compra(rs.getString("data_compra"));
                lista.add(g);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar garantias: " + e.getMessage());
        }
        return lista;
    }

    // BUSCAR GARANTIA POR ID
    public Garantia getGarantia(int id) {
        String sql = "SELECT * FROM garantia WHERE id_garantia = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Garantia g = new Garantia();
                g.setId_garantia(rs.getInt("id_garantia"));
                g.setId_cliente(rs.getInt("id_cliente"));
                g.setId_material(rs.getInt("id_material"));
                g.setId_demanda(rs.getInt("id_demanda"));
                g.setMeses_garantia(rs.getInt("meses_garantia"));
                g.setData_compra(rs.getString("data_compra"));
                return g;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar garantia: " + e.getMessage());
        }
        return null;
    }

    // ATUALIZAR GARANTIA
    public boolean atualizar(Garantia g) {
        String sql = "UPDATE garantia SET id_cliente=?, id_material=?, id_demanda=?, meses_garantia=?, data_compra=? WHERE id_garantia=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, g.getId_cliente());
            stmt.setInt(2, g.getId_material());
            if (g.getId_demanda() > 0) stmt.setInt(3, g.getId_demanda()); else stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setInt(4, g.getMeses_garantia());
            stmt.setString(5, g.getData_compra());
            stmt.setInt(6, g.getId_garantia());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar garantia: " + e.getMessage());
            return false;
        }
    }

    // DELETAR GARANTIA
    public boolean deletar(int id) {
        String sql = "DELETE FROM garantia WHERE id_garantia = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar garantia: " + e.getMessage());
            return false;
        }
    }
}
