
import java.sql.Timestamp;

/**
 *
 * @author sheff
 */
public class Showing {
    
    MovieAccess mal = new MovieAccess();
    
    int showingID;
    int screenID;
    String imdbID;
    Timestamp timestamp;
    Movie movie;

    public Showing(int showingID, int screenID, String imdbID, Timestamp timestamp) {
        this.showingID = showingID;
        this.screenID = screenID;
        this.imdbID = imdbID;
        this.timestamp = timestamp;
        initMovie();
    }
    
    private void initMovie() {
        movie = mal.getMovieByID(this.imdbID);
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
