
import java.sql.Timestamp;

/**
 *
 * @author sheff
 */
public class Showing {
    
    MovieAccess mal = new MovieAccess();
    
    int showingID;
    Screen screen;
    String imdbID;
    Timestamp timestamp;
    Movie movie;

    public Showing(int showingID, Screen screen, String imdbID, Timestamp timestamp) {
        this.showingID = showingID;
        this.screen = screen;
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
                + ", ScreenID:" + this.screen.id
                + ", imdbID:" + this.imdbID
                + ", Timestamp:" + this.timestamp
                +"}";
    }
    
}
