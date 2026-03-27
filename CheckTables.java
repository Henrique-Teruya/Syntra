import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckTables {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/Syntra?useSSL=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement stmt = con.createStatement();
            
            System.out.println("Tabelas no banco:");
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            
            // Ver chaves estrangeiras
            System.out.println("\nChaves estrangeiras de demandas_materiais:");
            ResultSet rsFK = stmt.executeQuery("SELECT CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME FROM information_schema.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_SCHEMA = 'syntra' AND TABLE_NAME = 'demandas_materiais'");
            while(rsFK.next()) {
                System.out.println(rsFK.getString(1) + " -> " + rsFK.getString(4) + "(" + rsFK.getString(5) + ")");
            }
            rsFK.close();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
