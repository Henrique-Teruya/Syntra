package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaomysql.Conexao;
import tabelaatributos.Chamados;

public class dao_chamados {

    private Connection conectar;

    public dao_chamados() {
        this.conectar = new Conexao().getConexao();
    }

    // INSERIR CHAMADO
    public boolean inserirDados(Chamados chamado) {
        String sql = "INSERT INTO chamados (id_cliente, descricao, data_abertura, status) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, chamado.getId_cliente());
            stmt.setString(2, chamado.getDescricao());
            stmt.setString(3, chamado.getData_abertura());
            stmt.setString(4, chamado.getStatus());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir chamado: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS
    public List<Chamados> listarTodos() {
        List<Chamados> lista = new ArrayList<>();
        if (conectar == null) return lista;
        String sql = "SELECT * FROM chamados";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Chamados ch = new Chamados();
                ch.setId_chamado(rs.getInt("id_chamado"));
                ch.setId_cliente(rs.getInt("id_cliente"));
                ch.setDescricao(rs.getString("descricao"));
                ch.setData_abertura(rs.getString("data_abertura"));
                ch.setStatus(rs.getString("status"));
                lista.add(ch);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar chamados: " + e.getMessage());
        }
        return lista;
    }

    // BUSCAR CHAMADO POR ID
    public Chamados getChamado(int id) {
        String sql = "SELECT * FROM chamados WHERE id_chamado = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Chamados ch = new Chamados();
                ch.setId_chamado(rs.getInt("id_chamado"));
                ch.setId_cliente(rs.getInt("id_cliente"));
                ch.setDescricao(rs.getString("descricao"));
                ch.setData_abertura(rs.getString("data_abertura"));
                ch.setStatus(rs.getString("status"));
                return ch;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar chamado: " + e.getMessage());
        }
        return null;
    }

    // ATUALIZAR CHAMADO
    public boolean atualizar(Chamados ch) {
        String sql = "UPDATE chamados SET id_cliente=?, descricao=?, data_abertura=?, status=? WHERE id_chamado=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, ch.getId_cliente());
            stmt.setString(2, ch.getDescricao());
            stmt.setString(3, ch.getData_abertura());
            stmt.setString(4, ch.getStatus());
            stmt.setInt(5, ch.getId_chamado());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar chamado: " + e.getMessage());
            return false;
        }
    }

    // DELETAR CHAMADO
    public boolean deletar(int id) {
        String sql = "DELETE FROM chamados WHERE id_chamado = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar chamado: " + e.getMessage());
            return false;
        }
    }
}
