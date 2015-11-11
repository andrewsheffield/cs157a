
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataAccess {

    public boolean createUser(String fname, String lname, String email) throws SQLException {
    
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                                   "user=root&password=");
            pstmt = conn.prepareStatement("INSERT INTO user(FirstName, LastName, Email) Values (?, ?, ?)");
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            
            pstmt.execute();
            
            System.out.println("CREATE: User " + fname + " has been added to the db.");
            
            return true;
        }
        catch(SQLException e) {
            System.out.println(e);
            return false;
        }
        finally {
            pstmt.close();
        }
        
    }
    
    public ArrayList searchUsersOneName(String searchString) throws SQLException {
        ArrayList arrayList = new ArrayList();
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                                   "user=root&password=");
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE FirstName LIKE ? OR LastName LIKE ?");
            pstmt.setString(1, "%" + searchString + "%");
            pstmt.setString(2, "%" + searchString + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(fname, lname, email);
                arrayList.add(newUser);
            }
            
            return arrayList;
            
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
       
    }

    public ArrayList searchUserEmail(String searchString) throws SQLException {
        ArrayList arrayList = new ArrayList();
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                                   "user=root&password=");
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE Email LIKE ?");
            pstmt.setString(1, "%" + searchString + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(fname, lname, email);
                arrayList.add(newUser);
            }
            
            return arrayList;
            
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList searchUserFirstAndLast(String searchFirst, String searchLast) throws SQLException {
        
        ArrayList arrayList = new ArrayList();
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                                   "user=root&password=");
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE FirstName LIKE ? AND LastName LIKE ?");
            pstmt.setString(1, "%" + searchFirst + "%");
            pstmt.setString(2, "%" + searchLast + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(fname, lname, email);
                arrayList.add(newUser);
            }
            
            return arrayList;
            
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    public boolean addFriend(int userID, int friendID) throws SQLException {
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                                   "user=root&password=");
            pstmt = conn.prepareStatement("INSERT INTO friend (UserID, FriendID) VALUES(?, ?)");
            pstmt.setInt(1, userID);
            pstmt.setInt(2, friendID);
            
            pstmt.execute();
            
            return true;
            
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        finally {
            pstmt.close();
        }
    }
    
}
