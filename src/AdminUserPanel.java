
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sheff
 */
public class AdminUserPanel extends JPanel {
    
    Controller cont;
    
    public AdminUserPanel(Controller cont) {
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
            gbc_searchUserLabel.gridy = y;
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
            
            JScrollPane userScrollPane = new JScrollPane();
            
            String[] userColumnNames = {"ID", "First Name", "Last Name", "Email", "Is Admin"};
            Object[][] userData = {};
            
            DefaultTableModel userModel = new DefaultTableModel(userData, userColumnNames){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    switch(columnIndex) {
                        case 0:
                            return String.class;
                        case 4:
                            return Boolean.class;
                    }
                    return String.class;
                }
                @Override
                 public boolean isCellEditable(int row, int column) {
                   switch(column){
                   case 4:
                       return true;
                   default:
                       return false;
                   }
                 }
            };
            for (User u : usersResult) {
                Object[] userArray = {u.id, u.fname, u.lname, u.email, u.isAdmin};
                userModel.addRow(userArray);
            }
            GridBagConstraints gbc_userScrollPane = new GridBagConstraints();
            gbc_userScrollPane.insets = new Insets(0, 0, 5, 0);
            gbc_userScrollPane.gridwidth = 10;
            gbc_userScrollPane.fill = GridBagConstraints.BOTH;
            gbc_userScrollPane.gridx = 0;
            y++;
            gbc_userScrollPane.gridy = y;
            JTable table = new JTable(userModel);
            userScrollPane.setViewportView(table);
            add(userScrollPane, gbc_userScrollPane);
            
            JButton updateIsAdmin = new JButton("Update");
            GridBagConstraints gbc_updateIsAdmin = new GridBagConstraints();
            gbc_updateIsAdmin.insets = new Insets(0, 0, 5, 5);
            gbc_updateIsAdmin.gridx = 1;
            y++;
            gbc_updateIsAdmin.gridy = y;
            add(updateIsAdmin, gbc_updateIsAdmin);
            
            updateIsAdmin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableModel data = table.getModel();
                    int rows = data.getRowCount();
                    for (int i=0;i<rows;i++) {
                        boolean adminBool = (Boolean) data.getValueAt(i, 4);
                        int id = (Integer) data.getValueAt(i, 0);
                        if (adminBool) {
                            cont.grantAdminAccess(id);
                        } else {
                            cont.removeAdminAccess(id);
                        }
                    }
                }
            });
    }
}
