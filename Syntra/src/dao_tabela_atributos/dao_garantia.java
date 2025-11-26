/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao_tabela_atributos;

import tabelaatributos.Garantia;
import conexaomysql.Conexao;
import java.sql.*;

public class dao_garantia {
    private Connection conectar;

    public dao_garantia() {
        this.conectar = new Conexao().getConexao();
    }

    public void inserirGarantia(Garantia garantia) {
        String sql = "INSERT INTO garantia (id_cliente, id_material, meses_garantia, data_compra) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conectar.prepareStatement(sql);
            stmt.setInt(1, garantia.getId_cliente());
            stmt.setInt(2, garantia.getId_material());
            stmt.setInt(3, garantia.getMeses_garantia());
            stmt.setString(4, garantia.getData_compra());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("? Erro ao inserir garantia: " + e.getMessage());
        }
    }
}
