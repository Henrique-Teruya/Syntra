package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexaomysql.Conexao;
import tabelaatributos.Demandas;

public class dao_demandas {
    private Conexao conexao;
    private Connection conectar;

    public dao_demandas() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }

    // Inserir demanda
    public void inserirDados(Demandas demanda) {
        String sql = "INSERT INTO demandas (descricao, data_solicitacao) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, demanda.getDescricao());
            stmt.setString(2, demanda.getData_solicitacao());
            stmt.execute();
            stmt.close();
            System.out.println("✅ Demanda inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir demanda: " + e.getMessage());
        }
    }

    // Buscar demanda por ID
    public Demandas getDemanda(int id) {
        String sql = "SELECT * FROM demandas WHERE id = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Demandas demanda = new Demandas();
                demanda.setId(rs.getInt("id"));
                demanda.setDescricao(rs.getString("descricao"));
                demanda.setData_solicitacao(rs.getString("data_solicitacao"));
                return demanda;
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar demanda: " + e.getMessage());
        }
        return null;
    }
}
