package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexaomysql.Conexao;
import tabelaatributos.Cliente;

public class dao_clientes {
    private Conexao conexao;
    private Connection conectar;

    public dao_clientes() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }

    // Inserir cliente
    public void inserirDados(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, grupo, CNPJ, CEP, Bairro, Rua) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getGrupo());
            stmt.setString(3, cliente.getCNPJ());
            stmt.setString(4, cliente.getCEP());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getRua());
            stmt.execute();
            stmt.close();
            System.out.println("✅ Cliente inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Buscar cliente por ID
    public Cliente getCliente(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setGrupo(rs.getString("grupo"));
                cliente.setCNPJ(rs.getString("CNPJ"));
                cliente.setCEP(rs.getString("CEP"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setRua(rs.getString("Rua"));
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }
}
