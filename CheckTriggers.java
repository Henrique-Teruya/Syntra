import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckTriggers {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Syntra?useSSL=false&serverTimezone=UTC", "root", "");
            Statement stmt = con.createStatement();
            
            ResultSet rs1 = stmt.executeQuery("SHOW TRIGGERS");
            while(rs1.next()) {
                 System.out.println("Trigger " + rs1.getString(1) + " on table " + rs1.getString(3) + " event " + rs1.getString(2) + ":");
                 System.out.println(rs1.getString(4));
                 System.out.println("---");
            }
            rs1.close();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
