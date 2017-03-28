package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;

public class HistoryUI extends JFrame
		{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8141494344180865577L;
	private JTable resultsTable;
	private DefaultTableModel defaultTableModel;
	private HistoryUIInterface delegate;

	JButton btnCancel, btnConfirm;
	JPanel panel;
	JScrollPane scrollPane;
	

	/*
	 * Setup class finders
	 */
	public HistoryUI(String[] titles) {

		// set the defaultTableModel to non editable by user clicking around
		defaultTableModel = new DefaultTableModel(0, 1) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				
			}
		};
		

		//defaultTableModel.addColumn("Data");

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

		// add small JPanel for Cancel and Confirm buttons
		JPanel buttom_panel = new JPanel();
		getContentPane().add(buttom_panel, BorderLayout.SOUTH);
		
		//create buttons from array list
		for (String title : titles){
			JButton button = new JButton(title);
			buttom_panel.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					delegate.didPressButton(title, resultsTable.getSelectedRow());
					
				}
			});
		}
	}

	//add a row to the end of table
	public void insert(HistoryDataObject e){
		defaultTableModel.addRow(new Object[] {e.toString()});
	}
	
	

	public void setDelegate(HistoryUIInterface delegate) {
		this.delegate = delegate;
	}

	/*
	 * Forcedly remove single selection in JTable
	 */
	private static class ForcedListSelectionModel
			extends
				DefaultListSelectionModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ForcedListSelectionModel() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		};
	}

}
