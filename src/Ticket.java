public class Ticket {
    public int userID;
    public int showingID;

    Ticket(int userID, int showingID) {
        this.userID = userID;
        this.showingID = showingID;
    }

    @Override
    public String toString() {
        return "{USER ID:" + userID + ", "  + "SHOWING ID:" + showingID +  "}";
    }
}
