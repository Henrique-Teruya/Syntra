package dao_tabela_atributos;

import conexaomysql.Conexao;
import tabelaatributos.Demandas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao_demandas {

    private Connection conectar;

    public dao_demandas() {
        this.conectar = new Conexao().getConexao();
    }

    // INSERIR DEMANDA + MATERIAIS
    public boolean inserirDadosComMateriais(Demandas demanda, List<int[]> itens) {
        String sqlDemanda = "INSERT INTO demandas (id_cliente, descricao, data_solicitacao, entregueSouN) VALUES (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO demandas_materiais (id_demanda, id_material, quantidade_usada) VALUES (?, ?, ?)";

        try {
            conectar.setAutoCommit(false);

            PreparedStatement stmtDem = conectar.prepareStatement(sqlDemanda, Statement.RETURN_GENERATED_KEYS);
            stmtDem.setInt(1, demanda.getId_cliente());
            stmtDem.setString(2, demanda.getDescricao());
            stmtDem.setString(3, demanda.getData_solicitacao());
            stmtDem.setString(4, demanda.getEntregueSouN());
            stmtDem.executeUpdate();

            ResultSet rs = stmtDem.getGeneratedKeys();
            int idGerado = 0;
            if (rs.next()) {
                idGerado = rs.getInt(1);
            } else {
                conectar.rollback();
                return false;
            }

            PreparedStatement stmtItem = conectar.prepareStatement(sqlItens);
            for (int[] item : itens) {
                stmtItem.setInt(1, idGerado);
                stmtItem.setInt(2, item[0]);
                stmtItem.setInt(3, item[1]);
                stmtItem.addBatch();
            }
            stmtItem.executeBatch();

            conectar.commit();
            return true;
        } catch (SQLException e) {
            try { conectar.rollback(); } catch (SQLException ex) { System.out.println("Erro no rollback: " + ex.getMessage()); }
            System.out.println("Erro ao inserir demanda com materiais: " + e.getMessage());
            return false;
        } finally {
            try { conectar.setAutoCommit(true); } catch (SQLException e) { System.out.println("Erro ao restaurar autocommit: " + e.getMessage()); }
        }
    }

    // INSERIR DEMANDA (sem materiais)
    public boolean inserirDados(Demandas demanda) {
        String sql = "INSERT INTO demandas (id_cliente, descricao, data_solicitacao, entregueSouN) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, demanda.getId_cliente());
            stmt.setString(2, demanda.getDescricao());
            stmt.setString(3, demanda.getData_solicitacao());
            stmt.setString(4, demanda.getEntregueSouN());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir demanda: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS
    public List<Demandas> listarTodos() {
        List<Demandas> lista = new ArrayList<>();
        String sql = "SELECT * FROM demandas";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demandas d = new Demandas();
                d.setId(rs.getInt("id"));
                d.setId_cliente(rs.getInt("id_cliente"));
                d.setDescricao(rs.getString("descricao"));
                d.setData_solicitacao(rs.getString("data_solicitacao"));
                d.setEntregueSouN(rs.getString("entregueSouN"));
                lista.add(d);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar demandas: " + e.getMessage());
        }
        return lista;
    }

    // BUSCAR POR ID
    public Demandas getDemanda(int id) {
        String sql = "SELECT * FROM demandas WHERE id = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Demandas d = new Demandas();
                d.setId(rs.getInt("id"));
                d.setId_cliente(rs.getInt("id_cliente"));
                d.setDescricao(rs.getString("descricao"));
                d.setData_solicitacao(rs.getString("data_solicitacao"));
                d.setEntregueSouN(rs.getString("entregueSouN"));
                return d;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar demanda: " + e.getMessage());
        }
        return null;
    }

    // ATUALIZAR DEMANDA
    public boolean atualizar(Demandas d) {
        String sql = "UPDATE demandas SET id_cliente=?, descricao=?, data_solicitacao=?, entregueSouN=? WHERE id=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, d.getId_cliente());
            stmt.setString(2, d.getDescricao());
            stmt.setString(3, d.getData_solicitacao());
            stmt.setString(4, d.getEntregueSouN());
            stmt.setInt(5, d.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar demanda: " + e.getMessage());
            return false;
        }
    }

    // DELETAR DEMANDA
    public boolean deletar(int id) {
        String sql = "DELETE FROM demandas WHERE id = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar demanda: " + e.getMessage());
            return false;
        }
    }
}
