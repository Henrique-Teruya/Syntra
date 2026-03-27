import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class CheckTables2 {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/Syntra?useSSL=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement stmt = con.createStatement();
            
            System.out.println("Colunas de estoque_mov:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM estoque_mov LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " - " + rsmd.getColumnTypeName(i));
            }
            rs.close();
            
            System.out.println("\nColunas de estoque_real:");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM estoque_real LIMIT 1");
            ResultSetMetaData rsmd2 = rs2.getMetaData();
            for(int i=1; i<=rsmd2.getColumnCount(); i++) {
                System.out.println(rsmd2.getColumnName(i) + " - " + rsmd2.getColumnTypeName(i));
            }
            rs2.close();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
