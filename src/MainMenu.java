import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu(Controller cont) {
		setLayout(new GridLayout(1,1));
		Display movies = new Display(cont);
		Friends friend = new Friends();
		Profile profile = new Profile();
		BoughtTickets tickets = new BoughtTickets();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.add("Showing", movies);
		tabbedPane.add("Friends", friend);
		tabbedPane.add("Profile", profile);
		tabbedPane.add("Future Events", tickets);
		add(tabbedPane);
		
	}
}
