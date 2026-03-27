import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class TestDB2 {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/Syntra?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String senha = "";
            
            Connection con = DriverManager.getConnection(url, usuario, senha);
            Statement stmt = con.createStatement();
            
            // Verificando colunas de estoque
            ResultSet rsEstoque = stmt.executeQuery("SELECT * FROM estoque LIMIT 1");
            ResultSetMetaData rsmdEstoque = rsEstoque.getMetaData();
            System.out.println("Colunas de estoque:");
            for (int i = 1; i <= rsmdEstoque.getColumnCount(); i++) {
                System.out.println("- " + rsmdEstoque.getColumnName(i) + " (" + rsmdEstoque.isNullable(i) + ")");
            }

            // Verificando colunas de garantia
            ResultSet rsGarantia = stmt.executeQuery("SELECT * FROM garantia LIMIT 1");
            ResultSetMetaData rsmdGarantia = rsGarantia.getMetaData();
            System.out.println("\nColunas de garantia:");
            for (int i = 1; i <= rsmdGarantia.getColumnCount(); i++) {
                System.out.println("- " + rsmdGarantia.getColumnName(i) + " (" + rsmdGarantia.isNullable(i) + ")");
            }

            // Verificando colunas de chamados
            ResultSet rsChamados = stmt.executeQuery("SELECT * FROM chamados LIMIT 1");
            ResultSetMetaData rsmdChamados = rsChamados.getMetaData();
            System.out.println("\nColunas de chamados:");
            for (int i = 1; i <= rsmdChamados.getColumnCount(); i++) {
                System.out.println("- " + rsmdChamados.getColumnName(i) + " (" + rsmdChamados.isNullable(i) + ")");
            }

            // Verificando colunas de cliente
            ResultSet rsCliente = stmt.executeQuery("SELECT * FROM cliente LIMIT 1");
            ResultSetMetaData rsmdCliente = rsCliente.getMetaData();
            System.out.println("\nColunas de cliente:");
            for (int i = 1; i <= rsmdCliente.getColumnCount(); i++) {
                System.out.println("- " + rsmdCliente.getColumnName(i) + " isNullable=" + rsmdCliente.isNullable(i));
            }

            // Ajustando a restrição NOT NULL para CNPJ se precisar
            try {
                stmt.executeUpdate("ALTER TABLE cliente MODIFY CNPJ VARCHAR(20) NOT NULL DEFAULT ''");
                System.out.println("CNPJ ajustado para NOT NULL");
            } catch(Exception e){
                System.out.println("Erro ao alterar CNPJ: " + e.getMessage());
            }

            rsEstoque.close();
            rsGarantia.close();
            rsChamados.close();
            rsCliente.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
