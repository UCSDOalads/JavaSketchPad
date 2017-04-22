package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


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
		resultsTable.setModel(defaultTableModel);
		resultsTable.setSelectionModel(new ForcedListSelectionModel());
		
		// scroll option
		scrollPane = new JScrollPane(resultsTable);
		add(scrollPane, BorderLayout.CENTER);

		//set title for table
		TitledBorder border = new TitledBorder("Actions History");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);
	    resultsTable.setTableHeader(null);
	    
	    //set size
	    setPreferredSize(new Dimension(160, 300));
	    
		//create buttons for panel
		createButtons(titles);
	}
	

	private void createButtons(String[] titles){
		// add small JPanel for buttons
		button_panel= new JPanel();
		add(button_panel, BorderLayout.SOUTH);

		//loop through arr and add buttons
		for (String title : titles){
			JButton button = new JButton(title);
			button_panel.add(button);
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

}

