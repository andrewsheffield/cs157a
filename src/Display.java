import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Display extends JPanel {
	private JTextField query;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBuy;
	private JCheckBox chckbxJ;
	private JCheckBox chckbxA;
	private JCheckBox chckbxB;
	private JCheckBox chckbxC;
	private JCheckBox chckbxD;
	private JLabel lblFilters;
        private Controller cont;

	/**
	 * Create the panel.
	 */
	public Display(Controller cont) {
            this.cont = cont;
            this.render();
	}
        
        
        public void render() {
            
            GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		query = new JTextField();
		GridBagConstraints gbc_query = new GridBagConstraints();
		gbc_query.gridwidth = 9;
		gbc_query.insets = new Insets(0, 0, 5, 5);
		gbc_query.fill = GridBagConstraints.HORIZONTAL;
		gbc_query.gridx = 0;
		gbc_query.gridy = 0;
		add(query, gbc_query);
		query.setColumns(10);
		
		Object[] columnNames = {"ID", "Movie Name", "Screen #", "Show Time", "IMAX", "3D", "XD", "DBOX", "Quanitity"};
		Object[][] data = {};
                

                
               
                
		DefaultTableModel model = new DefaultTableModel(data, columnNames){
			
		
		  @Override
          public Class<?> getColumnClass(int columnIndex) {
			  switch(columnIndex) {
			  case 8:
				  return Integer.class;
                          }
			  return String.class;
          }
		   @Override
		    public boolean isCellEditable(int row, int column) {
		      switch(column){
		      case 8:
		    	  return column == 8;
		      default:
		    	  return false;
		      }
		    }
		};
                
                if (query.getText().isEmpty()) {
                    ArrayList<Showing> showings = cont.model.getUpcomingShows();
                    for (Showing s : showings) {
                                            System.out.println(showings);

                        Object[] showing = {s.showingID, s.movie.title, s.screen.name, s.timestamp, s.screen.imax, s.screen.threeD, s.screen.xd, s.screen.dbox, new Integer(0)};
                        model.addRow(showing);
                    }   
                } else {
                    ArrayList<Showing> showings = cont.model.getShowingsByMovieTitle(query.getText());
                    for (Showing s : showings) {
                        Object[] showing = {s.showingID, s.movie.title, s.screen.name, s.timestamp, s.screen.imax, s.screen.threeD, s.screen.xd, s.screen.dbox, new Integer(0)};
                        model.addRow(showing);
                    }   
                }

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.gridwidth = 10;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		add(scrollPane, gbc_table);
		table = new JTable(model);
		scrollPane.setViewportView(table);
                //TableColumn column = table.getColumnModel().getColumn(2);
                
		//create sorter
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sort);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		 
		sort.setSortKeys(sortKeys);
		sort.sort();
	
		table.getTableHeader().setReorderingAllowed(false);
		JButton btnSearch = new JButton("Search");
		
                GridBagConstraints gbc_btnSearch = new GridBagConstraints();
                gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
                gbc_btnSearch.gridx = 9;
                gbc_btnSearch.gridy = 0;
                add(btnSearch, gbc_btnSearch);
                
                btnSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        render();
                    }
                });
                                
		
		btnBuy = new JButton("Buy");
                
                btnBuy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i=0;i<model.getRowCount(); i++) {
                            int showingID = (Integer) model.getValueAt(i, 0);
                            int amount = (Integer) model.getValueAt(i, 8);
                            System.out.println(showingID);
                            System.out.println(amount);
                            
                            cont.purchaseTickets(showingID, 3);
                        }
                    }
                });

		
		lblFilters = new JLabel("Filters:");
		GridBagConstraints gbc_lblFilters = new GridBagConstraints();
		gbc_lblFilters.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilters.gridx = 1;
		gbc_lblFilters.gridy = 1;
		add(lblFilters, gbc_lblFilters);
		
		chckbxJ = new JCheckBox("j");
		GridBagConstraints gbc_chckbxJ = new GridBagConstraints();
		gbc_chckbxJ.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJ.gridx = 2;
		gbc_chckbxJ.gridy = 1;
		add(chckbxJ, gbc_chckbxJ);
		
		chckbxA = new JCheckBox("a");
		GridBagConstraints gbc_chckbxA = new GridBagConstraints();
		gbc_chckbxA.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxA.gridx = 3;
		gbc_chckbxA.gridy = 1;
		add(chckbxA, gbc_chckbxA);
		
		chckbxB = new JCheckBox("b");
		GridBagConstraints gbc_chckbxB = new GridBagConstraints();
		gbc_chckbxB.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxB.gridx = 4;
		gbc_chckbxB.gridy = 1;
		add(chckbxB, gbc_chckbxB);
		
		chckbxC = new JCheckBox("c");
		GridBagConstraints gbc_chckbxC = new GridBagConstraints();
		gbc_chckbxC.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxC.gridx = 5;
		gbc_chckbxC.gridy = 1;
		add(chckbxC, gbc_chckbxC);
		
		chckbxD = new JCheckBox("d");
		GridBagConstraints gbc_chckbxD = new GridBagConstraints();
		gbc_chckbxD.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxD.gridx = 6;
		gbc_chckbxD.gridy = 1;
		add(chckbxD, gbc_chckbxD);
		
		
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.gridx = 9;
		gbc_btnBuy.gridy = 3;
		add(btnBuy, gbc_btnBuy);
        }

	
	
}
