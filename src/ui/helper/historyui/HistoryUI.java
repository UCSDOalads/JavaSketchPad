package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;




/**
 * create historyUI
 *
 */
public class HistoryUI extends JFrame
		{
	private static final long serialVersionUID = 8141494344180865577L;
	private JTable resultsTable;
	private DefaultTableModel defaultTableModel;
	private HistoryUIInterface delegate;

	
	private JPanel panel;
	private JScrollPane scrollPane;
	
	@SuppressWarnings("rawtypes")
	private Stack<ArrayList> delete_history;

	
	
	/**
	 * Setup historyUI
	 * 
	 * param: receive a list of button names
	 */
	public HistoryUI(String[] titles) {

		// set the defaultTableModel to non editable by user clicking around
		defaultTableModel = new DefaultTableModel(0, 1) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				
			}
		};

		// big JPanel window
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// show result
		resultsTable = new JTable();
		resultsTable.setModel(defaultTableModel);
		resultsTable.setSelectionModel(new ForcedListSelectionModel());
		
		// scroll option
		scrollPane = new JScrollPane(resultsTable);
		panel.add(scrollPane, BorderLayout.CENTER);

		// add small JPanel for buttons
		JPanel button_panel = new JPanel();
		getContentPane().add(button_panel, BorderLayout.SOUTH);
		
		//initialize buttons
		createButtons(titles, button_panel);
		
		//stack, used to stored deleted rows
		delete_history = new Stack<>();
	}

	/**
	 * create buttons
	 * param: a list of buttons' name and button panel
	 */
	public void createButtons(String[] titles, JPanel button_panel){
		//loop through arr and add buttons
		for (String title : titles){
			JButton button = new JButton(title);
			button_panel.add(button);
			button.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					//if delete button is pressed, delete selected rows
					if(title == "delete"){
						int[] indices = resultsTable.getSelectedRows();
						if(indices.length != 0)
							removeSeveralRows(indices);
					}
					//if exit button pressed, close window
					else if(title == "exit"){
						System.exit(EXIT_ON_CLOSE);
					}
					
					//if revert button pressed, revert to last version
					else if(title =="revert"){
						revert();
					}
					
					//if clear button pressed, delete all rows in table
					else if(title =="clear"){
						int num_rows = resultsTable.getRowCount();
						int[] indices = IntStream.range(0, num_rows).toArray();
						removeSeveralRows(indices);
					}
					delegate.didPressButton(title, resultsTable.getSelectedRow());
					
				}
			});
		}
	}


	/**
	 * revert deleted rows
	 */
	@SuppressWarnings("rawtypes")
	public void revert(){
		
		if(!delete_history.isEmpty()){
			ArrayList arr = delete_history.pop();
			//get the deleted rows and corresponding data 
			int[] indices = (int[]) arr.get(0);
			HistoryDataObject[] objects = (HistoryDataObject[]) arr.get(1);
			
			//insert data to its row
			for(int i = 0; i<indices.length; i++){
				defaultTableModel.insertRow(indices[i], new Object[] {objects[i]} );
			}
		}
	}
	
	
	
	/**
	 * add a row to the end of table
	 * receive a HistoryDataObject
	 */
	public void insert(HistoryDataObject e){
		defaultTableModel.addRow(new Object[] {e});
	}
	

	/**
	 * remove several selected rows from table and stores them into stack
	 * param: an array of rows number to be removed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeSeveralRows(int[] indices){
		//create a arr to hold the deleted rows and its data
		ArrayList arr = new ArrayList<>();
		HistoryDataObject[] objects = new HistoryDataObject[indices.length];
		Arrays.sort(indices);
		
		//loop through indices and delete
		for(int i=indices.length-1;i>=0;i--){
			objects[i] = (HistoryDataObject) resultsTable.getModel().getValueAt(indices[i], 0);
			defaultTableModel.removeRow(indices[i]);
		}
		
		//stores deleted rows and its data to delete_history stack
		arr.add(indices);
		arr.add(objects);
		delete_history.push(arr);
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
			this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		};
	}

}
