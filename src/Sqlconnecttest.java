import java.awt.EventQueue;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Controller cont = new Controller();

        //cont.login("andrew@test.com", "password");
        //cont.updateUserInfo("Andrew", "Sheffield", "andrew@test.com");
        
        //System.out.println(model.getPurchasedTickets(2));
        
        
        //System.out.println(model.getUserSearchResults("sheff"));
        
        //Timestamp currentTimestamp = Timestamp.valueOf("2014-11-30 14:00:00.0");
        
        //cont.createNewShowing(1, "tt2294629", currentTimestamp);
        //System.out.println(model.getUpcomingShows());
        //cont.purchaseTickets(1, 3);
        //cont.cancelTicket(1, 5);
        
        //System.out.println(model.getPurchasedTickets());
        //cont.sendTicketToFriend(2, 1, 1);
        //System.out.println(model.getUser());
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame(cont);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
