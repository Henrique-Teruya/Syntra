package conexaomysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao() {
        try {
            String url = "jdbc:mysql://localhost:3306/KGP?useSSL=false&serverTimezone=UTC";
            String usuario = "root";      // troque se usar outro usuário
            String senha = "";            // coloque sua senha (se houver)

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conectar = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao com KGP OK");
            return conectar;
        } catch (Exception e) {
            System.out.println("Erro ao tentar conectar-se: " + e);
        }
        return null;
    }
}
