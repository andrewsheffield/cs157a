
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author sheff
 */
public class AdminPanel extends JPanel {
    
    private final Controller cont;
    
    public AdminPanel(Controller cont) {
        this.cont = cont;
        render();
    }
    
    public void render() {
        this.removeAll();
        this.revalidate();
        
        int y = 0;
        
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        layout.rowHeights = new int[]{0, 0, 0, 0, 0};
        layout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        setLayout(layout);
        
        //USER SEARCH AND SET ADMIN
            //USER SEARCH FIELD AND BUTTON
            JLabel searchUserLabel = new JLabel("Search Users to add as Admin:");
            GridBagConstraints gbc_searchUserLabel = new GridBagConstraints();
            gbc_searchUserLabel.insets = new Insets(0, 0, 5, 5);
            gbc_searchUserLabel.gridx = 0;
            gbc_searchUserLabel.gridy = y++;
            add(searchUserLabel, gbc_searchUserLabel);
            
            JTextField searchUserField = new JTextField();
            GridBagConstraints gbc_searchUserField = new GridBagConstraints();
            gbc_searchUserField.insets = new Insets(0, 0, 5, 5);
            gbc_searchUserField.fill = GridBagConstraints.HORIZONTAL;
            gbc_searchUserField.gridx = 0;
            y++;
            gbc_searchUserField.gridy = y;
            searchUserField.setColumns(10);
            add(searchUserField, gbc_searchUserField);
            
            JButton searchUserButton = new JButton("Search");
            GridBagConstraints gbc_searchUserButton = new GridBagConstraints();
            gbc_searchUserButton.insets = new Insets(0, 0, 5, 5);
            gbc_searchUserButton.gridx = 1;
            gbc_searchUserButton.gridy = y;
            add(searchUserButton, gbc_searchUserButton);
            
            searchUserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cont.setUserSearch(searchUserField.getText());
                    render();
                }
            });
            
            //Search Results
            ArrayList<User> usersResult = cont.model.getUserSearchResults();
            for (int i=0;i<usersResult.size() && i<5; i++) {
                User user = usersResult.get(i);
                JLabel userString = new JLabel(user.fname + " " + user.lname + " " + user.email);
                GridBagConstraints gbc_userString = new GridBagConstraints();
                gbc_userString.insets = new Insets(0, 0, 5, 5);
                gbc_userString.gridx = 0;
                y++;
                gbc_userString.gridy = y;
                add(userString, gbc_userString);
                if (!user.isAdmin) {
                    JButton addAsAdminBtn = new JButton("Set Admin");
                    GridBagConstraints gbc_addAsAdminBtn = new GridBagConstraints();
                    gbc_addAsAdminBtn.insets = new Insets(0, 0, 5, 5);
                    gbc_addAsAdminBtn.gridx = 1;
                    gbc_addAsAdminBtn.gridy = y;
                    add(addAsAdminBtn, gbc_addAsAdminBtn);
                    addAsAdminBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cont.grantAdminAccess(user.id);
                            render();
                        }
                    });
                } else {
                    JButton removeAsAdminBtn = new JButton("Remove Admin");
                    GridBagConstraints gbc_removeAsAdminBtn = new GridBagConstraints();
                    gbc_removeAsAdminBtn.insets = new Insets(0, 0, 5, 5);
                    gbc_removeAsAdminBtn.gridx = 1;
                    gbc_removeAsAdminBtn.gridy = y;
                    add(removeAsAdminBtn, gbc_removeAsAdminBtn);
                    removeAsAdminBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cont.removeAdminAccess(user.id);
                            render();
                        }
                    });
                }
            }
            
            
        
        //CREATE SCREENS SHOW SCREENS REMOVE SCREEN BUTTON
        
        //SEARCH MOVIES
        
        //ADD SHOWING, SHOW SHOWINGS, REMOVE SHOWINGS
    }
    
}
