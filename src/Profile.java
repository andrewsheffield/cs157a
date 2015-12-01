import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class Profile extends JPanel {
	private JTextField newEmail;
	private JTextField newFName;
	private JTextField newLName;
	private JTable table;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	//private Controller cont;
	private JTable table_friends;
	/**
	 * Create the panel.
	 */
	public Profile(Controller cont) {
		//this.cont = cont;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 50, 54, 0, 0, 50, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		table_friends = new JTable();

		
		Object[] columnN = {"FriendsID", "Email", "First Name", "Last Name", "Remove"};
		Object[][] data1 = {};
		DefaultTableModel model1 = new DefaultTableModel(data1, columnN){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0:
                        return String.class;
                    case 5:
                        return Boolean.class;
                }
                return String.class;
            }
            @Override
             public boolean isCellEditable(int row, int column) {
               switch(column){
               case 5:
                   return true;
               default:
                   return false;
               }
             }
        };
		if(cont.model.getFriends() != null){
			ArrayList<User> friends = cont.model.getFriends();
                        friends.add(0, cont.model.getUser());
			for (User s : friends) {
	
				Object[] person = {s.id, s.email, s.fname, s.lname,new Boolean(false) };
				model1.addRow(person);
			}  
		}
		
		scrollPane2 = new JScrollPane();
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.gridwidth = 5;
		gbc_table_1.gridheight = 4;
		gbc_table_1.insets = new Insets(0, 0, 5, 5);
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 2;
		gbc_table_1.gridy = 1;
		add(scrollPane2, gbc_table_1);
		
		table_friends = new JTable(model1);
                table_friends.changeSelection(0, 0, false, false);
		scrollPane2.setViewportView(table_friends);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 8;
		gbc_lblEmail.gridy = 1;
		add(lblEmail, gbc_lblEmail);
		
		JLabel lblEmailR = new JLabel(cont.model.getUser().email);
		GridBagConstraints gbc_lblEmailR = new GridBagConstraints();
		gbc_lblEmailR.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailR.gridx = 9;
		gbc_lblEmailR.gridy = 1;
		add(lblEmailR, gbc_lblEmailR);
		
		newEmail = new JTextField();
		GridBagConstraints gbc_newEmail = new GridBagConstraints();
		gbc_newEmail.insets = new Insets(0, 0, 5, 5);
		gbc_newEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_newEmail.gridx = 11;
		gbc_newEmail.gridy = 1;
		add(newEmail, gbc_newEmail);
		newEmail.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 8;
		gbc_lblFirstName.gridy = 2;
		add(lblFirstName, gbc_lblFirstName);
		
		JLabel lblFName = new JLabel(cont.model.getUser().fname);
		GridBagConstraints gbc_lblFName = new GridBagConstraints();
		gbc_lblFName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFName.gridx = 9;
		gbc_lblFName.gridy = 2;
		add(lblFName, gbc_lblFName);
		
		newFName = new JTextField();
		GridBagConstraints gbc_newFName = new GridBagConstraints();
		gbc_newFName.insets = new Insets(0, 0, 5, 5);
		gbc_newFName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newFName.gridx = 11;
		gbc_newFName.gridy = 2;
		add(newFName, gbc_newFName);
		newFName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 8;
		gbc_lblLastName.gridy = 3;
		add(lblLastName, gbc_lblLastName);
		
		JLabel lblLName = new JLabel(cont.model.getUser().lname);
		GridBagConstraints gbc_lblLName = new GridBagConstraints();
		gbc_lblLName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLName.gridx = 9;
		gbc_lblLName.gridy = 3;
		add(lblLName, gbc_lblLName);
		
		newLName = new JTextField(cont.model.getUser().lname);
		GridBagConstraints gbc_newLName = new GridBagConstraints();
		gbc_newLName.insets = new Insets(0, 0, 5, 5);
		gbc_newLName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newLName.gridx = 11;
		gbc_newLName.gridy = 3;
		add(newLName, gbc_newLName);
		newLName.setColumns(10);
		
		Object[] columnNames = {"ID", "Movie Name", "Screen #", "TimeStamp", "Quantity",};
		Object[][] data = {};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable();
		
		JButton btnDeleteFriend = new JButton("Delete Friend");
		GridBagConstraints gbc_btnDeleteFriend = new GridBagConstraints();
		gbc_btnDeleteFriend.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteFriend.gridx = 7;
		gbc_btnDeleteFriend.gridy = 4;
		add(btnDeleteFriend, gbc_btnDeleteFriend);
                
                if (table_friends.isRowSelected(0)) btnDeleteFriend.setEnabled(false);
                table_friends.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (table_friends.isRowSelected(0)) btnDeleteFriend.setEnabled(false);
                        else btnDeleteFriend.setEnabled(true);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
		
		JButton btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 11;
		gbc_btnUpdate.gridy = 4;
		add(btnUpdate, gbc_btnUpdate);
		
				scrollPane = new JScrollPane();
				GridBagConstraints gbc_table = new GridBagConstraints();
				gbc_table.fill = GridBagConstraints.BOTH;
				gbc_table.insets = new Insets(0, 0, 5, 0);
				gbc_table.gridwidth = 12;
				gbc_table.gridx = 1;
				gbc_table.gridy = 5;
				add(scrollPane, gbc_table);
				table = new JTable(model);
				scrollPane.setViewportView(table);
				
				JButton btnTableUpdate = new JButton("Table Update");
				GridBagConstraints gbc_btnTableUpdate = new GridBagConstraints();
				gbc_btnTableUpdate.insets = new Insets(0, 0, 0, 5);
				gbc_btnTableUpdate.gridx = 11;
				gbc_btnTableUpdate.gridy = 6;
				add(btnTableUpdate, gbc_btnTableUpdate);

	}

}
