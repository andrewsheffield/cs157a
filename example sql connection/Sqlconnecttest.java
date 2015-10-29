/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnecttest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/JavaTest?" +
                                   "user=root&password=");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
            
            while (rs.next()) {
                System.out.println(rs.getString("fName"));
            }
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        
    }
    
}
