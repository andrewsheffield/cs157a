import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
	/**
	 * Create the panel.
	 */
	public Profile() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 50, 0, 0, 50, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);
		
		JLabel lblBlah = new JLabel("Blah");
		GridBagConstraints gbc_lblBlah = new GridBagConstraints();
		gbc_lblBlah.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlah.gridx = 6;
		gbc_lblBlah.gridy = 1;
		add(lblBlah, gbc_lblBlah);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 2;
		add(lblEmail, gbc_lblEmail);
		
		JLabel lblBlah_1 = new JLabel("Blah");
		GridBagConstraints gbc_lblBlah_1 = new GridBagConstraints();
		gbc_lblBlah_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlah_1.gridx = 6;
		gbc_lblBlah_1.gridy = 2;
		add(lblBlah_1, gbc_lblBlah_1);
		
		newEmail = new JTextField();
		GridBagConstraints gbc_newEmail = new GridBagConstraints();
		gbc_newEmail.insets = new Insets(0, 0, 5, 0);
		gbc_newEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_newEmail.gridx = 9;
		gbc_newEmail.gridy = 2;
		add(newEmail, gbc_newEmail);
		newEmail.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 3;
		add(lblFirstName, gbc_lblFirstName);
		
		JLabel lblBlah_2 = new JLabel("Blah");
		GridBagConstraints gbc_lblBlah_2 = new GridBagConstraints();
		gbc_lblBlah_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlah_2.gridx = 6;
		gbc_lblBlah_2.gridy = 3;
		add(lblBlah_2, gbc_lblBlah_2);
		
		newFName = new JTextField();
		GridBagConstraints gbc_newFName = new GridBagConstraints();
		gbc_newFName.insets = new Insets(0, 0, 5, 0);
		gbc_newFName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newFName.gridx = 9;
		gbc_newFName.gridy = 3;
		add(newFName, gbc_newFName);
		newFName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 4;
		add(lblLastName, gbc_lblLastName);
		
		JLabel lblBlah_3 = new JLabel("Blah");
		GridBagConstraints gbc_lblBlah_3 = new GridBagConstraints();
		gbc_lblBlah_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlah_3.gridx = 6;
		gbc_lblBlah_3.gridy = 4;
		add(lblBlah_3, gbc_lblBlah_3);
		
		newLName = new JTextField();
		GridBagConstraints gbc_newLName = new GridBagConstraints();
		gbc_newLName.insets = new Insets(0, 0, 5, 0);
		gbc_newLName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newLName.gridx = 9;
		gbc_newLName.gridy = 4;
		add(newLName, gbc_newLName);
		newLName.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridx = 9;
		gbc_btnUpdate.gridy = 5;
		add(btnUpdate, gbc_btnUpdate);
		
		Object[] columnNames = {"test1", "test2", "test3"};
		Object[][] data = {{"a", "b", "c"},
				{"e","f","g"},
				{"q","r","s"},
				{"q","r","s"},
				{"q","r","s"},
				{"q","r","s"},
				{"q","r","s"},
				{"q","r","s"},
				{"q","r","s"},
				{"a","c","e"}};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		scrollPane = new JScrollPane();
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 10;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 6;
		add(scrollPane, gbc_table);
		table = new JTable(model);
		scrollPane.setViewportView(table);

	}

}
