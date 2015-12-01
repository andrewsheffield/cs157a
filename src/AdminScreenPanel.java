
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class AdminScreenPanel extends JPanel {
    
    Controller cont;
    
    public AdminScreenPanel(Controller cont) {
        this.cont = cont;
        render();
    }
    
    public void render(){
        this.removeAll();
        this.revalidate();
        
        int y = 0;
        
        JLabel screenLabel = new JLabel("Create New Screen");
            GridBagConstraints gbc_screenLabel = new GridBagConstraints();
            gbc_screenLabel.insets = new Insets(0, 0, 5, 5);
            gbc_screenLabel.gridx = 0;
            gbc_screenLabel.gridy = y;
            add(screenLabel, gbc_screenLabel);
            
            y++;
            
            //Set Name of new
            JLabel screenNameLabel = new JLabel("Name:");
            GridBagConstraints gbc_screenNameLabel = new GridBagConstraints();
            gbc_screenNameLabel.insets = new Insets(0, 0, 5, 5);
            gbc_screenNameLabel.gridx = 0;
            gbc_screenNameLabel.gridy = y;
            add(screenNameLabel, gbc_screenNameLabel);
            
            JTextField screenNameField = new JTextField();
            GridBagConstraints gbc_screenNameField = new GridBagConstraints();
            gbc_screenNameField.insets = new Insets(0, 0, 5, 5);
            gbc_screenNameField.fill = GridBagConstraints.HORIZONTAL;
            gbc_screenNameField.gridx = 1;
            gbc_screenNameField.gridy = y;
            screenNameField.setColumns(10);
            add(screenNameField, gbc_screenNameField);
            
            //Set sieze of new
            JLabel screenSizeLabel = new JLabel("Size:");
            GridBagConstraints gbc_screenSizeLabel = new GridBagConstraints();
            gbc_screenSizeLabel.insets = new Insets(0, 0, 5, 5);
            gbc_screenSizeLabel.gridx = 0;
            gbc_screenSizeLabel.gridy = y;
            add(screenSizeLabel, gbc_screenSizeLabel);
            
            JTextField screenSizeField = new JTextField();
            GridBagConstraints gbc_screenSizeField = new GridBagConstraints();
            gbc_screenSizeField.insets = new Insets(0, 0, 5, 5);
            gbc_screenSizeField.fill = GridBagConstraints.HORIZONTAL;
            gbc_screenSizeField.gridx = 1;
            gbc_screenSizeField.gridy = y;
            screenSizeField.setColumns(3);
            add(screenSizeField, gbc_screenSizeField);
            
            JCheckBox imaxCheck = new JCheckBox("IMAX");
            add(imaxCheck);
            
            JCheckBox threeDCheck = new JCheckBox("3D");
            add(threeDCheck);
            
            JCheckBox xdCheck = new JCheckBox("XD");
            add(xdCheck);
            
            JCheckBox dboxCheck = new JCheckBox("DBOX");
            add(dboxCheck);
            
            JButton createScreenButton = new JButton("Create");
            GridBagConstraints gbc_createScreenButton = new GridBagConstraints();
            gbc_createScreenButton.insets = new Insets(0, 0, 5, 5);
            gbc_createScreenButton.gridx = 1;
            gbc_createScreenButton.gridy = y;
            add(createScreenButton, gbc_createScreenButton);
            
            createScreenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = screenNameField.getText();
                    int size = Integer.parseInt(screenSizeField.getText());
                    boolean imax = imaxCheck.isSelected();
                    boolean threeD = threeDCheck.isSelected();
                    boolean dbox = dboxCheck.isSelected();
                    boolean xd = xdCheck.isSelected();
                    cont.createScreen(name, size, imax, threeD, dbox, xd);
                    render();
                }
            });
            
            //Search Results
            ArrayList<Screen> screenResult = cont.model.getAllScreens();
            
            JScrollPane screenScrollPane = new JScrollPane();
            
            String[] screenColumnNames = {"ID", "Name", "Size", "IMAX", "3D", "XD", "DBOX", "Remove"};
            Object[][] screenData = {};
            
            DefaultTableModel screenModel = new DefaultTableModel(screenData, screenColumnNames){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    switch(columnIndex) {
                        case 7:
                            return Boolean.class;
                    }
                    return String.class;
                }
                @Override
                 public boolean isCellEditable(int row, int column) {
                   switch(column){
                       case 7:
                           return true;
                   default:
                       return false;
                   }
                 }
            };
            for (Screen s : screenResult) {
                Object[] screenArray = {s.id, s.name, s.size, s.imax, s.threeD, s.xd, s.dbox, new Boolean(false)};
                screenModel.addRow(screenArray);
            }
            GridBagConstraints gbc_screenScrollPane = new GridBagConstraints();
            gbc_screenScrollPane.insets = new Insets(0, 0, 5, 0);
            gbc_screenScrollPane.gridwidth = 10;
            gbc_screenScrollPane.fill = GridBagConstraints.BOTH;
            gbc_screenScrollPane.gridx = 0;
            y++;
            gbc_screenScrollPane.gridy = y;
            JTable screenTable = new JTable(screenModel);
            screenScrollPane.setViewportView(screenTable);
            add(screenScrollPane, gbc_screenScrollPane);
            
            JButton removeScreenBtn = new JButton("Remove Selected");
            GridBagConstraints gbc_removeScreenBtn = new GridBagConstraints();
            gbc_removeScreenBtn.insets = new Insets(0, 0, 5, 5);
            gbc_removeScreenBtn.gridx = 1;
            y++;
            gbc_removeScreenBtn.gridy = y;
            add(removeScreenBtn, gbc_removeScreenBtn);
            
            removeScreenBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowCount = screenTable.getRowCount();
                    TableModel data = screenTable.getModel();
                    for (int i=0;i<rowCount;i++) {
                        int id = (Integer) data.getValueAt(i, 0);
                        boolean delete = (boolean) data.getValueAt(i, 7);
                        if (delete) {
                            cont.deleteScreen(id);
                            render();
                        }
                    }
                }
            });
    }
 }
