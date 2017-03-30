package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class HistoryUI extends JFrame implements ActionListener
		{
	private static final long serialVersionUID = 8141494344180865577L;
	private JTable resultsTable;
	private DefaultTableModel defaultTableModel;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel button_panel;
	
	private Stack<HistoryDataObject[]> delete_history;

	/**
	 * Setup historyUI
	 */
	public HistoryUI(String[] titles){

		// set the defaultTableModel to non editable by user clicking around
		setDefaultTableModel(new DefaultTableModel(0, 1) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		// big JPanel window
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// show result
		setResultsTable(new JTable());
		getResultsTable().setModel(getDefaultTableModel());
		getResultsTable().setSelectionModel(new ForcedListSelectionModel());
		
		// scroll option
		scrollPane = new JScrollPane(getResultsTable());
		panel.add(scrollPane, BorderLayout.CENTER);

		//stack, used to stored deleted rows
		setDelete_history(new Stack<>());
		
		//create buttons for panel
		createButtons(titles);
	}
	

	private void createButtons(String[] titles){
		// add small JPanel for buttons
		button_panel= new JPanel();
		getContentPane().add(button_panel, BorderLayout.SOUTH);

		//loop through arr and add buttons
		for (String title : titles){
			JButton button = new JButton(title);
			button_panel.add(button);
			button.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("delete")){
			System.out.println("aaaa");
			int[] indices = getResultsTable().getSelectedRows();
			if(indices.length != 0)
				removeSeveralRows(indices);
		}
		//if exit button pressed, close window
		else if(btn.getText().equals("exit")){
			System.exit(EXIT_ON_CLOSE);
		}
		
		//if revert button pressed, revert to last version
		else if(btn.getText().equals("revert")){
			revert();
		}
		
		//if clear button pressed, delete all rows in table
		else if(btn.getText().equals("clear")){
			int num_rows = getResultsTable().getRowCount();
			int[] indices = IntStream.range(0, num_rows).toArray();
			removeSeveralRows(indices);
		}
	}

	/**
	 * revert deleted rows
	 */
	public void revert(){
		
		if(!getDelete_history().isEmpty()){
			HistoryDataObject[] objects = delete_history.pop();
			//insert data to its row
			for(int i = 0; i<objects.length; i++){
				getDefaultTableModel().insertRow(objects[i].getRow(), new Object[] {objects[i]} );
			}
		}
	}
	
	
	
	/**
	 * add a row to the end of table
	 * receive a HistoryDataObject
	 */
	public void insert(HistoryDataObject e){
		getDefaultTableModel().addRow(new Object[] {e});
		getDefaultTableModel().fireTableDataChanged();
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
			objects[i] = (HistoryDataObject) getResultsTable().getModel().getValueAt(indices[i], 0);
			objects[i].setRow(indices[i]);
			getDefaultTableModel().removeRow(indices[i]);
		}
		
		//stores deleted rows and its data to delete_history stack
		getDelete_history().push(objects);
	}

	public void setDelegate(HistoryUIInterface delegate) {
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}


	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JTable getResultsTable() {
		return resultsTable;
	}


	public void setResultsTable(JTable resultsTable) {
		this.resultsTable = resultsTable;
	}

	public Stack<HistoryDataObject[]> getDelete_history() {
		return delete_history;
	}

	public void setDelete_history( Stack<HistoryDataObject[]> delete_history) {
		this.delete_history = delete_history;
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

