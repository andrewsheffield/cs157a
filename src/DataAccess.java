
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataAccess {
    
    private String databaseURI = "jdbc:mysql://localhost/theaterpro?user=root&password=";
    private String salt = "SaltySalt";

    //sign up a new user
    public User createUser(String fname, String lname, String email, String password) throws SQLException {
    
        PreparedStatement pstmt = null;
        
        String newUserQuery = "INSERT INTO user(FirstName, LastName, Email, isAdmin) Values (?, ?, ?, ?)";
        String newAuthQuery = "INSERT INTO auth(UserID, hPassword) VALUES (?, ?)";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Create new user
            pstmt = conn.prepareStatement(newUserQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            pstmt.setBoolean(4, false);
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            User user = new User(rs.getInt(1), fname, lname, email, false);
            
            //Add auth for new User
            int hPassword = (password+salt).hashCode();
            pstmt = conn.prepareStatement(newAuthQuery);
            pstmt.setInt(1, user.id);
            pstmt.setInt(2, hPassword);
            pstmt.execute();
            
            //Return the user that was created
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
    
    //login a user
    public User login(String email, String password) throws SQLException {
        PreparedStatement pstmt = null;
        
        String userQuery = "SELECT * FROM user WHERE Email=?";
        String authQuery = "SELECT * FROM auth WHERE UserID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Get the User we are attempting to authenticate
            pstmt = conn.prepareStatement(userQuery);
            pstmt.setString(1, email);
            ResultSet urs = pstmt.executeQuery();
            if (urs.next()) {
                User user = new User(urs.getInt("UserID"), urs.getString("FirstName"), urs.getString("LastName"), urs.getString("Email"), urs.getBoolean("isAdmin"));
                
                //get Auth values
                int hPassword = (password+salt).hashCode();
                pstmt = conn.prepareStatement(authQuery);
                pstmt.setInt(1, user.id);
                ResultSet ars = pstmt.executeQuery();
                ars.next();
                int dbhPassword = ars.getInt("hPassword");

                if (dbhPassword == hPassword) return user;
                else return null;
            }
            
            return null;
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
                boolean isAdmin = rs.getBoolean("isAdmin");
                
                User newUser = new User(id, fname, lname, email, isAdmin);
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
                boolean isAdmin = rs.getBoolean("isAdmin");
                
                User newUser = new User(id, fname, lname, email, isAdmin);
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
                boolean isAdmin = rs.getBoolean("isAdmin");
                
                User newUser = new User(id, fname, lname, email, isAdmin);
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
