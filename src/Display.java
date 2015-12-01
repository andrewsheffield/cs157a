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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Display extends JPanel {
	private JTextField query;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBuy;
        private JButton btnSearch;
	private JCheckBox chckbx3D;
	private JCheckBox chckbxIMAX;
	private JCheckBox chckbxXD;
	private JCheckBox chckbxDBOX;
	private JLabel lblFilters;
        private Controller cont;
        private JButton btnLogout;

	/**
	 * Create the panel.
	 */
	public Display(Controller cont) {
            this.cont = cont;
	}
        
        
        public void render() {
            this.removeAll();
            this.revalidate();
            GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			query = new JTextField();
			GridBagConstraints gbc_query = new GridBagConstraints();
			gbc_query.gridwidth = 10;
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
	                
	                if (cont.model.showingSearchQuery.isEmpty()) {
	                    ArrayList<Showing> showings = cont.model.getUpcomingShows();
	                    for (Showing s : showings) {
	
	                        Object[] showing = {s.showingID, s.movie.title, s.screen.name, s.timestamp, s.screen.imax, s.screen.threeD, s.screen.xd, s.screen.dbox, new Integer(0)};
	                        model.addRow(showing);
	                    }   
	                } else {
	                    ArrayList<Showing> showings = cont.model.getShowingsByMovieTitle(cont.model.showingSearchQuery);
	                    for (Showing s : showings) {
	                        Object[] showing = {s.showingID, s.movie.title, s.screen.name, s.timestamp, s.screen.imax, s.screen.threeD, s.screen.xd, s.screen.dbox, new Integer(0)};
	                        model.addRow(showing);
	                    }   
	                }
	
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.insets = new Insets(0, 0, 5, 0);
			gbc_table.gridwidth = 11;
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 2;
			add(scrollPane, gbc_table);
			table = new JTable(model);
			scrollPane.setViewportView(table);

			//create sorter
			TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
			table.setRowSorter(sort);
			
			List<RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 1;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sort.setSortKeys(sortKeys);
			sort.sort();
		
			table.getTableHeader().setReorderingAllowed(false);
			

	        Action action = new AbstractAction()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                TableCellListener tcl = (TableCellListener)e.getSource();
	                int column = tcl.getColumn();

	                if (column == 8)
	                {
	                    int row = tcl.getRow();
	                    int quantity = ((int) tcl.getNewValue());
	                    TableModel model = tcl.getTable().getModel();

	                   
	                    model.setValueAt(quantity, row, 8);

	                }
	            }
	        };

	      TableCellListener tcl = new TableCellListener(table, action);
			
			btnSearch = new JButton("Search");
			
	                GridBagConstraints gbc_btnSearch = new GridBagConstraints();
	                gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
	                gbc_btnSearch.gridx = 10;
	                gbc_btnSearch.gridy = 0;
	                add(btnSearch, gbc_btnSearch);
	                
	                btnSearch.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        cont.model.showingSearchQuery = query.getText();
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
	                            
	                            cont.purchaseTickets(showingID, amount);
                                    cont.model.view.render();
	                        }
	                    }
	                });
	
			
			lblFilters = new JLabel("Filters:");
			GridBagConstraints gbc_lblFilters = new GridBagConstraints();
			gbc_lblFilters.insets = new Insets(0, 0, 5, 5);
			gbc_lblFilters.gridx = 2;
			gbc_lblFilters.gridy = 1;
			add(lblFilters, gbc_lblFilters);
			
			chckbx3D = new JCheckBox("3D");
			GridBagConstraints gbc_chckbx3D = new GridBagConstraints();
			gbc_chckbx3D.insets = new Insets(0, 0, 5, 5);
			gbc_chckbx3D.gridx = 3;
			gbc_chckbx3D.gridy = 1;
			add(chckbx3D, gbc_chckbx3D);
			
			chckbxIMAX = new JCheckBox("IMAX");
			GridBagConstraints gbc_chckbxIMAX = new GridBagConstraints();
			gbc_chckbxIMAX.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxIMAX.gridx = 4;
			gbc_chckbxIMAX.gridy = 1;
			add(chckbxIMAX, gbc_chckbxIMAX);
			
			chckbxXD = new JCheckBox("XD");
			GridBagConstraints gbc_chckbxXD = new GridBagConstraints();
			gbc_chckbxXD.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxXD.gridx = 5;
			gbc_chckbxXD.gridy = 1;
			add(chckbxXD, gbc_chckbxXD);
			
			chckbxDBOX = new JCheckBox("DBOX");
			GridBagConstraints gbc_chckbxDBOX = new GridBagConstraints();
			gbc_chckbxDBOX.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxDBOX.gridx = 6;
			gbc_chckbxDBOX.gridy = 1;
			add(chckbxDBOX, gbc_chckbxDBOX);
			
			btnLogout = new JButton("Logout");
			GridBagConstraints gbc_btnLogout = new GridBagConstraints();
			gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
			gbc_btnLogout.gridx = 2;
			gbc_btnLogout.gridy = 3;
			add(btnLogout, gbc_btnLogout);
			
			
			GridBagConstraints gbc_btnBuy = new GridBagConstraints();
			gbc_btnBuy.gridx = 10;
			gbc_btnBuy.gridy = 3;
			add(btnBuy, gbc_btnBuy);
        }

	
	
}
