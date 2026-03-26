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
        if (conectar == null) { System.out.println("Erro: conexao nula."); return false; }
        if (cliente == null) { System.out.println("Erro: objeto cliente está nulo."); return false; }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) { System.out.println("Erro: nome vazio."); return false; }

        String sql = "INSERT INTO cliente (nome, grupo, CPF, CNPJ, CEP, Bairro, Rua) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getGrupo());
            stmt.setString(3, cliente.getCPF() != null ? cliente.getCPF() : "");
            stmt.setString(4, cliente.getCNPJ() != null ? cliente.getCNPJ() : "");
            stmt.setString(5, cliente.getCEP());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getRua());
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
        if (conectar == null) { return lista; }

        String sql = "SELECT id_cliente, nome, grupo, CPF, CNPJ, CEP, Bairro, Rua FROM cliente";

        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setGrupo(rs.getString("grupo"));

                String cpf = rs.getString("CPF") != null ? rs.getString("CPF") : "";
                String cnpj = rs.getString("CNPJ") != null ? rs.getString("CNPJ") : "";

                c.setCPF(cpf);
                c.setCNPJ(cnpj);

                if (!cpf.isEmpty()) {
                    c.setTipo("PESSOA");
                    c.setCNPJ(""); // Limpeza conforme AGENTS.md
                } else if (!cnpj.isEmpty()) {
                    c.setTipo("EMPRESA");
                    c.setCPF(""); // Limpeza conforme AGENTS.md
                } else {
                    c.setTipo("DESCONHECIDO");
                }

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
        if (conectar == null || id <= 0) { return null; }

        String sql = "SELECT id_cliente, nome, grupo, CPF, CNPJ, CEP, Bairro, Rua FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setGrupo(rs.getString("grupo"));

                String cpf = rs.getString("CPF") != null ? rs.getString("CPF") : "";
                String cnpj = rs.getString("CNPJ") != null ? rs.getString("CNPJ") : "";

                cliente.setCPF(cpf);
                cliente.setCNPJ(cnpj);

                if (!cpf.isEmpty()) {
                    cliente.setTipo("PESSOA");
                } else if (!cnpj.isEmpty()) {
                    cliente.setTipo("EMPRESA");
                }

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
        if (conectar == null) return false;
        String sql = "UPDATE cliente SET nome=?, grupo=?, CPF=?, CNPJ=?, CEP=?, Bairro=?, Rua=? WHERE id_cliente=?";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getGrupo());
            stmt.setString(3, c.getCPF() != null ? c.getCPF() : "");
            stmt.setString(4, c.getCNPJ() != null ? c.getCNPJ() : "");
            stmt.setString(5, c.getCEP());
            stmt.setString(6, c.getBairro());
            stmt.setString(7, c.getRua());
            stmt.setInt(8, c.getId_cliente());
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
        if (conectar == null) return false;
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
