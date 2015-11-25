
import java.sql.Timestamp;

public class Ticket {
    
    public Showing showing;
    public Timestamp purchaseTimestamp;

    Ticket(Showing showing, Timestamp purchaseTimestamp) {
        this.showing = showing;
        this.purchaseTimestamp = purchaseTimestamp;
    }

    @Override
    public String toString() {
        return showing.toString();
    }
}
