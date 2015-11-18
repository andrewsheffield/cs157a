
/**
 *
 * @author sheff
 */
public class Movie {
    
    String title;
    String imdbID;
    String plot;

    public Movie(String title, String imdbID, String plot) {
        this.title = title;
        this.imdbID = imdbID;
        this.plot =plot;
    }
    
    @Override
    public String toString() {
        return "{imdbID:" + this.imdbID + ", title:" + this.title + ", plot:" + this.plot + "}";
    }
    
}
