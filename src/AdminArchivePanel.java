
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sheff
 */
class AdminArchivePanel extends JPanel {
    
    Controller cont;

    public AdminArchivePanel(Controller cont) {
        this.cont = cont;
        render();
    }
    
    public void render() {
        this.removeAll();
        this.revalidate();
        
        Date startingDate = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startingDate);
        gc.add(Calendar.YEAR, -1);
        Date result = gc.getTime();
        
        Timestamp timestamp = new Timestamp(result.getTime());
        
        JLabel archiveLabel = new JLabel("Archive Shows and Screens before " + timestamp.toString());
        add(archiveLabel);
        
        JButton archivebtn = new JButton("Archive");
        add(archivebtn);
        
        archivebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.archive(timestamp);
                cont.model.view.render();
            }
        });
        
    }
    
}
