
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataAccess {
    
    String databaseURI = "jdbc:mysql://localhost/theaterpro?user=root&password=";

    public User createUser(String fname, String lname, String email) throws SQLException {
    
        PreparedStatement pstmt = null;
        
        String newUserQuery = "INSERT INTO user(FirstName, LastName, Email) Values (?, ?, ?)";
        String getNewUserQuery = "SELECT * FROM user ORDER BY UserID DESC LIMIT 1";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(newUserQuery);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            
            pstmt.execute();
            
            
            pstmt = conn.prepareCall(getNewUserQuery);
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            
            User user = new User(rs.getInt("UserID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"));
            
            System.out.println("CREATE: User " + user.id + " has been added to the db.");
            
            return user;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
        
    }
    
    public ArrayList searchUsersOneName(String searchString) throws SQLException {
        ArrayList arrayList = new ArrayList();
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE FirstName LIKE ? OR LastName LIKE ?");
            pstmt.setString(1, "%" + searchString + "%");
            pstmt.setString(2, "%" + searchString + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(id, fname, lname, email);
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
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE Email LIKE ?");
            pstmt.setString(1, "%" + searchString + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(id, fname, lname, email);
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
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement("SELECT * FROM user WHERE FirstName LIKE ? AND LastName LIKE ?");
            pstmt.setString(1, "%" + searchFirst + "%");
            pstmt.setString(2, "%" + searchLast + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User newUser = new User(id, fname, lname, email);
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
            Connection conn = DriverManager.getConnection(databaseURI);
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
