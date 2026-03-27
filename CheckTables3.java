import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckTables3 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Syntra?useSSL=false&serverTimezone=UTC", "root", "");
            Statement stmt = con.createStatement();
            
            ResultSet rs1 = stmt.executeQuery("SHOW CREATE TABLE estoque_mov");
            if(rs1.next()) System.out.println(rs1.getString(2));
            rs1.close();
            
            System.out.println("\n---------------------------\n");
            
            ResultSet rs2 = stmt.executeQuery("SHOW CREATE TABLE estoque_real");
            if(rs2.next()) System.out.println(rs2.getString(2));
            rs2.close();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
