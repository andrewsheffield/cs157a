import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/theaterpro", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            
            while (rs.next()) {
                System.out.println(rs.getString("fName"));
            }
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        
    }
    
}
