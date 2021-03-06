
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


public class DataAccess {
    
    private final String databaseURI = "jdbc:mysql://localhost/theaterpro?user=root&password=";
    private final String salt = "SaltySalt";

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
        
        String authQuery = "SELECT * FROM user,auth WHERE Email=? AND user.UserID=auth.UserID;";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Get the User we are attempting to authenticate
            pstmt = conn.prepareStatement(authQuery);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("UserID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getBoolean("isAdmin"));
                
                //get Auth values
                int hPassword = (password+salt).hashCode();
                int dbhPassword = rs.getInt("hPassword");

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
    
  //Buy multiple tickets
    public boolean purchaseTickets(int userID, int showingID, int amount) throws SQLException {

        PreparedStatement pstmt = null;
        Connection conn = DriverManager.getConnection(databaseURI);
        String valueAmount = "INSERT INTO ticket(UserID, ShowingID) Values (?, ?)";
        
        try {

            pstmt = conn.prepareStatement(valueAmount);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, showingID);
            
            for (int i=0; i < amount; i++) {
                pstmt.execute();
            }

            return true;
        }
        catch(SQLException e) {
            System.out.println("Purchase Query-" + e);
            return false;
        }
        finally {
            pstmt.close();
        }
    }
    
    //Cancel purchase
    public void cancelTicket(int userID, int showingID, int amount) throws SQLException {

        PreparedStatement pstmt = null;

        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement("DELETE FROM ticket WHERE userID = ? and showingID = ? limit ?");
            pstmt.setInt(1, userID);
            pstmt.setInt(2, showingID);
            pstmt.setInt(3, amount);

            pstmt.execute();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
    }

    public Screen createScreen(String name, int size, boolean imax, boolean threeD, boolean dbox, boolean xd) throws SQLException {
        PreparedStatement pstmt = null;
        
        String newScreenQuery = "INSERT INTO screen(Name, Size, IMAX, 3D, DBOX, XD) Values (?, ?, ?, ?, ?, ?)";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Create new user
            pstmt = conn.prepareStatement(newScreenQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setInt(2, size);
            pstmt.setBoolean(3, imax);
            pstmt.setBoolean(4, threeD);
            pstmt.setBoolean(5, dbox);
            pstmt.setBoolean(6, xd);
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            Screen screen = new Screen(rs.getInt(1), name, size, imax, threeD, dbox, xd);
            
            //Return the user that was created
            return screen;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList getAllScreens() throws SQLException {
        ArrayList arrayList = new ArrayList();
        
        PreparedStatement pstmt = null;
        String getScreensQuery = "SELECT * FROM screen";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(getScreensQuery);

            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("ScreenID");
                String name = rs.getString("name");
                int size = rs.getInt("size");
                boolean imax = rs.getBoolean("IMAX");
                boolean threeD = rs.getBoolean("3D");
                boolean dbox = rs.getBoolean("DBOX");
                boolean xd = rs.getBoolean("XD");
                
                Screen screen = new Screen(id, name, size, imax, threeD, dbox, xd);
                arrayList.add(screen);
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

    boolean deleteScreen(int id) throws SQLException {
        PreparedStatement pstmt = null;
        String deleteScreenQuery = "DELETE FROM screen WHERE ScreenID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(deleteScreenQuery);
            pstmt.setInt(1, id);
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

    boolean grantAdminAccess(int id) throws SQLException {
        PreparedStatement pstmt = null;
        String grantAdminQuery = "UPDATE user SET isAdmin=1 WHERE UserID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(grantAdminQuery);
            pstmt.setInt(1, id);
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

    boolean removeAdminAccess(int id) throws SQLException {
        PreparedStatement pstmt = null;
        String grantAdminQuery = "UPDATE user SET isAdmin=0 WHERE UserID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(grantAdminQuery);
            pstmt.setInt(1, id);
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

    public void createNewShowing(int screenID, String imdbID, Timestamp timestamp) throws SQLException {
        PreparedStatement pstmt = null;
        
        String newShowingQuery = "INSERT INTO showing(ScreenID, imdbID, startTimestamp) VALUES (?, ?, ?)";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);

            pstmt = conn.prepareStatement(newShowingQuery);
            pstmt.setInt(1, screenID);
            pstmt.setString(2, imdbID);
            pstmt.setTimestamp(3, timestamp);
            pstmt.execute();
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList<Showing> getUpcomingShows(Timestamp currentTimestamp) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Showing> upcomingShows = new ArrayList();
        
        String getShowingQuery = "SELECT * FROM showing,screen WHERE startTimestamp>=? AND showing.ScreenID=screen.ScreenID ORDER BY startTimestamp ASC";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Create new user
            pstmt = conn.prepareStatement(getShowingQuery);
            pstmt.setTimestamp(1, currentTimestamp);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int showingID = rs.getInt("ShowingID");
                int screenID = rs.getInt("ScreenID");
                String screenName = rs.getString("Name");
                int size = rs.getInt("Size");
                String imdbID = rs.getString("imdbID");
                Timestamp timestamp = rs.getTimestamp("startTimestamp");
                boolean isIMAX = rs.getBoolean("IMAX");
                boolean is3D = rs.getBoolean("3D");
                boolean isXD = rs.getBoolean("XD");
                boolean isDBOX = rs.getBoolean("DBOX");
                
                
                Screen screen = new Screen(screenID, screenName, size, isIMAX, is3D, isXD, isDBOX);
                Showing showing = new Showing(showingID, screen, imdbID, timestamp);
                upcomingShows.add(showing);
            }
            
            
            //Return the user that was created
            return upcomingShows;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    
    }

    public void removeShowing(int showingID) throws SQLException{
        PreparedStatement pstmt = null;
        String deleteShowingQuery = "DELETE FROM showing WHERE ShowingID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(deleteShowingQuery);
            pstmt.setInt(1, showingID);
            pstmt.execute();

            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList<User> getFriends(int id) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<User> friends = new ArrayList();
        
        String getFriendsQuery = "SELECT * FROM friendview WHERE UserID=? ORDER BY LastName ASC, Firstname ASC";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(getFriendsQuery);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int friendID = rs.getInt("FriendID");
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                
                User friend = new User(friendID, fname, lname, email, false);
                friends.add(friend);
            }
            
            return friends;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    
    }

    public void removeFriend(int id, int friendID) throws SQLException {
        PreparedStatement pstmt = null;
        
        String removeFriendQuery = "DELETE FROM friend WHERE UserID=? AND FriendID=?;";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            pstmt = conn.prepareStatement(removeFriendQuery);
            pstmt.setInt(1, id);
            pstmt.setInt(2, friendID);
            pstmt.execute();

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList<Showing> getShowingByimdbID(String imdbID) throws SQLException {
        Date date = new Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        PreparedStatement pstmt = null;
        ArrayList<Showing> showings = new ArrayList();
        
        String getShowingQuery = "SELECT * FROM showing,screen WHERE imdbID=? AND showing.ScreenID=screen.ScreenID ORDER BY startTimestamp ASC";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            //Create new user
            pstmt = conn.prepareStatement(getShowingQuery);
            pstmt.setString(1, imdbID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int showingID = rs.getInt("ShowingID");
                int screenID = rs.getInt("ScreenID");
                String screenName = rs.getString("Name");
                int size = rs.getInt("Size");
                Timestamp timestamp = rs.getTimestamp("startTimestamp");
                boolean isIMAX = rs.getBoolean("IMAX");
                boolean is3D = rs.getBoolean("3D");
                boolean isXD = rs.getBoolean("XD");
                boolean isDBOX = rs.getBoolean("DBOX");
                
                
                Screen screen = new Screen(screenID, screenName, size, isIMAX, is3D, isXD, isDBOX);
                Showing showing = new Showing(showingID, screen, imdbID, timestamp);
                showings.add(showing);
            }
            
            
            //Return the user that was created
            return showings;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList<Ticket> getPurchasedTickets(int userID) throws SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Ticket> tickets = new ArrayList();
        
        String getTicketsForUserQuery = "select * from ticket, showing, screen where UserID=? AND ticket.ShowingID=showing.ShowingID AND showing.ScreenID=screen.ScreenID";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(getTicketsForUserQuery);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                int showingID = rs.getInt("ShowingID");
                int screenID = rs.getInt("ScreenID");
                String screenName = rs.getString("Name");
                int size = rs.getInt("Size");
                String imdbID = rs.getString("imdbID");
                Timestamp timestamp = rs.getTimestamp("startTimestamp");
                boolean isIMAX = rs.getBoolean("IMAX");
                boolean is3D = rs.getBoolean("3D");
                boolean isXD = rs.getBoolean("XD");
                boolean isDBOX = rs.getBoolean("DBOX");           
                Timestamp startTime = rs.getTimestamp("startTimestamp");
                Timestamp purchaseTime = rs.getTimestamp("ticket.updatedAt");
                
                Screen screen = new Screen(screenID, screenName, size, isIMAX, is3D, isXD, isDBOX);
                Showing showing = new Showing(showingID, screen, imdbID, startTime);
                Ticket ticket = new Ticket(showing, purchaseTime);
                tickets.add(ticket);
            }
            
            return tickets;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    public void sendTicketToFriend(int userID, int FriendID, int ShowingID, int amount) throws SQLException {
        PreparedStatement pstmt = null;
        
        String sendTicketQuery = "UPDATE ticket set UserID=? where UserID=? AND ShowingID=? limit ?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            pstmt = conn.prepareStatement(sendTicketQuery);
            pstmt.setInt(1, FriendID);
            pstmt.setInt(2, userID);
            pstmt.setInt(3, ShowingID);
            pstmt.setInt(4, amount);
            pstmt.execute();

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
    }

    public ArrayList<Ticket> getTicketHistory(int UserID) throws SQLException {
        Date date = new Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        PreparedStatement pstmt = null;
        ArrayList<Ticket> tickets = new ArrayList();
        
        String getPurchaseHistoryQuery = "select * from ticket, showing, screen where UserID=? AND ticket.ShowingID=showing.ShowingID AND showing.ScreenID=screen.ScreenID AND showing.startTimestamp<?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            pstmt = conn.prepareStatement(getPurchaseHistoryQuery);
            pstmt.setInt(1, UserID);
            pstmt.setTimestamp(2, currentTimestamp);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                int showingID = rs.getInt("ShowingID");
                int screenID = rs.getInt("ScreenID");
                String screenName = rs.getString("Name");
                int size = rs.getInt("Size");
                String imdbID = rs.getString("imdbID");
                boolean isIMAX = rs.getBoolean("IMAX");
                boolean is3D = rs.getBoolean("3D");
                boolean isXD = rs.getBoolean("XD");
                boolean isDBOX = rs.getBoolean("DBOX");           
                Timestamp startTime = rs.getTimestamp("startTimestamp");
                Timestamp purchaseTime = rs.getTimestamp("ticket.updatedAt");
                
                Screen screen = new Screen(screenID, screenName, size, isIMAX, is3D, isXD, isDBOX);
                Showing showing = new Showing(showingID, screen, imdbID, startTime);
                Ticket ticket = new Ticket(showing, purchaseTime);
                tickets.add(ticket);
            }
            
            return tickets;
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
        finally {
            pstmt.close();
        }
    }

    void updateUserInfo(int id, String fname, String lname, String email) throws SQLException {
        PreparedStatement pstmt = null;
        
        String updateUserInfoQuery = "UPDATE user SET FirstName=?, LastName=?, Email=? WHERE UserID=?";
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            pstmt = conn.prepareStatement(updateUserInfoQuery);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            pstmt.execute();

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        finally {
            pstmt.close();
        }
        
    }

    void archive(Timestamp ts) throws SQLException {
        PreparedStatement pstmt = null;
        
        String archiveQuery = "{call archive(?)}";
        
        
        try {
            Connection conn = DriverManager.getConnection(databaseURI);
            
            pstmt = conn.prepareStatement(archiveQuery);
            pstmt.setTimestamp(1, ts);
            pstmt.execute();
            
            
            

        }
        catch(SQLException e) {
            System.out.println("Archive Query-" + e);
        }
        finally {
            pstmt.close();
        }
    }
    
    
    
}
