package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexaomysql.Conexao;
import tabelaatributos.Cliente;

public class dao_clientes {

    private Conexao conexao;
    private Connection conectar;

    public dao_clientes() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }

    // INSERIR CLIENTE
    public boolean inserirDados(Cliente cliente) {
        if (cliente == null) { System.out.println("Erro: objeto cliente está nulo."); return false; }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) { System.out.println("Erro: nome vazio."); return false; }

        String sql = "INSERT INTO cliente (nome, grupo, tipo, CPF, CNPJ, CEP, Bairro, Rua) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getGrupo());
            stmt.setString(3, cliente.getTipo());
            stmt.setString(4, cliente.getCPF());
            stmt.setString(5, cliente.getCNPJ());
            stmt.setString(6, cliente.getCEP());
            stmt.setString(7, cliente.getBairro());
            stmt.setString(8, cliente.getRua());
            stmt.execute();
            stmt.close();
            System.out.println("Cliente inserido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro SQL ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setGrupo(rs.getString("grupo"));
                c.setTipo(rs.getString("tipo"));
                c.setCPF(rs.getString("CPF"));
                c.setCNPJ(rs.getString("CNPJ"));
                c.setCEP(rs.getString("CEP"));
                c.setBairro(rs.getString("Bairro"));
                c.setRua(rs.getString("Rua"));
                lista.add(c);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR CLIENTE POR ID
    public Cliente getCliente(int id) {
        if (id <= 0) { System.out.println("Erro: ID inválido."); return null; }

        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setGrupo(rs.getString("grupo"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setCNPJ(rs.getString("CNPJ"));
                cliente.setCEP(rs.getString("CEP"));
                cliente.setBairro(rs.getString("Bairro"));
                cliente.setRua(rs.getString("Rua"));
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro SQL ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    // ATUALIZAR CLIENTE
    public boolean atualizar(Cliente c) {
        String sql = "UPDATE cliente SET nome=?, grupo=?, tipo=?, CPF=?, CNPJ=?, CEP=?, Bairro=?, Rua=? WHERE id_cliente=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getGrupo());
            stmt.setString(3, c.getTipo());
            stmt.setString(4, c.getCPF());
            stmt.setString(5, c.getCNPJ());
            stmt.setString(6, c.getCEP());
            stmt.setString(7, c.getBairro());
            stmt.setString(8, c.getRua());
            stmt.setInt(9, c.getId_cliente());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // DELETAR CLIENTE
    public boolean deletar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }
}
