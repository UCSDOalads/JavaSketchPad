package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Font;


/**
 * create historyUI
 *
 */
public class HistoryUI extends JPanel 
		{
	private JTable resultsTable;
	private DefaultTableModel defaultTableModel;
	private JScrollPane scrollPane;
	private JPanel button_panel;
	private ArrayList<JButton> buttonArray = new ArrayList<JButton>();
	
	private HistoryUIInterface delegate;

	/**
	 * Setup historyUI
	 */
	public HistoryUI(String[] titles){

		// set the defaultTableModel to non editable by user clicking around
		this.defaultTableModel = (new DefaultTableModel(0, 1) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		setLayout(new BorderLayout(0, 0));
	

		// show result
		this.resultsTable= (new JTable());
		resultsTable.setFont(new Font("Apple LiSung", Font.PLAIN, 16));
		resultsTable.setShowHorizontalLines(false);
		resultsTable.setShowVerticalLines(false);
		resultsTable.setShowGrid(false);
		resultsTable.setBorder(null);
		resultsTable.setBackground(new Color(150,150,150));
		resultsTable.setForeground(Color.WHITE);
		resultsTable.setModel(defaultTableModel);
		resultsTable.setSelectionModel(new ForcedListSelectionModel());
		
		
		// scroll option
		scrollPane = new JScrollPane(resultsTable);
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(null);
		add(scrollPane, BorderLayout.CENTER);

		//set title for table
	
		TitledBorder border = new TitledBorder("Actions History");
		border.setTitleColor(Color.WHITE);
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);
	    resultsTable.setTableHeader(null);
	    
	    //set size
	    setPreferredSize(new Dimension(160, 300));
	    
		//create buttons for panel
		createButtons(titles);
		
		setBackground(Color.black);
	}
	

	private void createButtons(String[] titles){
		// add small JPanel for buttons
		FlowLayout fl_button_panel = new FlowLayout();
		button_panel= new JPanel(fl_button_panel);
		button_panel.setBorder(null);

		button_panel.setBackground(new Color(150,150,150));
		add(button_panel, BorderLayout.SOUTH);

		//loop through arr and add buttons
		for (String title : titles){
			JButton button = new JButton(title);
			button_panel.add(button);
			buttonArray.add(button);
			button.setEnabled(false);
			button.setEnabled(false);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					delegate.didPressButton(title, resultsTable.getSelectedRow());
				}
			});
		}
	}


	
	
	
	/**
	 * add a row to the end of table
	 * receive a HistoryDataObject
	 */
	public void insert(HistoryDataObject e){
		defaultTableModel.addRow(new Object[] {e});
		defaultTableModel.fireTableDataChanged();
	}
	

	/**
	 * remove several selected rows from table and stores them into stack
	 * param: an array of rows number to be removed
	 */
	public void removeSeveralRows(int[] indices){
		//create a arr to hold the deleted rows and its data
		HistoryDataObject[] objects = new HistoryDataObject[indices.length];
		Arrays.sort(indices);
		
		//loop through indices and delete
		for(int i=indices.length-1;i>=0;i--){
			objects[i] = (HistoryDataObject) defaultTableModel.getValueAt(indices[i], 0);
			objects[i].setRow(indices[i]);
			defaultTableModel.removeRow(indices[i]);
		}
		
	}
	
	public void clear(){
		int rowCount = defaultTableModel.getRowCount();
		for(int i = 0; i < rowCount; i++){
			defaultTableModel.removeRow(0);
		}
		defaultTableModel.fireTableDataChanged();
	}

	public void setDelegate(HistoryUIInterface delegate) {
		this.delegate = delegate;
	}

	/**
	 * Forcedly multiple interval selection in JTable
	 */
	private static class ForcedListSelectionModel
			extends
				DefaultListSelectionModel {
		private static final long serialVersionUID = 1L;

		public ForcedListSelectionModel() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		};
	}

	/**
	 * Getting the array of buttons available
	 */
	public ArrayList<JButton> getButtonArray() {
		return buttonArray;
	}
	
}

