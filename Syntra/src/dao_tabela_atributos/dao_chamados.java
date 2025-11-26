package dao_tabela_atributos;

import tabelaatributos.Chamados;
import conexaomysql.Conexao;
import java.sql.*;

public class dao_chamados {

    private Connection conectar;

    public dao_chamados() {
        this.conectar = new Conexao().getConexao();
    }

    public void inserirChamado(Chamados chamado) {
        String sql = "INSERT INTO chamados (id_cliente, descricao, data_abertura, status) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, chamado.getId_cliente());
            stmt.setString(2, chamado.getDescricao());
            stmt.setString(3, chamado.getData_abertura());
            stmt.setString(4, chamado.getStatus());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("? Erro ao inserir chamado: " + e.getMessage());
        }
    }
}
