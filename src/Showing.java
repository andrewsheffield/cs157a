
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
class Showing {
    
    int showingID;
    int screenID;
    String imdbID;
    Timestamp timestamp;

    public Showing(int showingID, int screenID, String imdbID, Timestamp timestamp) {
        this.showingID = showingID;
        this.screenID = screenID;
        this.imdbID = imdbID;
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "{" 
                + "ShowingID:" + this.showingID
                + ", ScreenID:" + this.screenID
                + ", imdbID:" + this.imdbID
                + ", Timestamp:" + this.timestamp
                +"}";
    }
    
}
