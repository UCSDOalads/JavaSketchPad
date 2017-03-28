package interation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classpathutil.ClassSearch;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;

public class historyUI extends JFrame
		{
	private JTable resultsTable;
	private ClassSearch searchUtil;
	private DefaultTableModel defaultTableModel;
	private ClassSearchFrameDelegateInterface delegate;

	private Thread runningThread;
	JButton btnCancel, btnConfirm;
	JPanel panel;
	JPanel panel_2;
	JScrollPane scrollPane;
	

	/*
	 * Setup class finders
	 */
	public historyUI(String[] titles) {

		// set the defaultTableModel to non editable by user clicking around
		defaultTableModel = new DefaultTableModel(0, 1) {
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

		// search text field on top
		searchUtil = ClassSearch.sharedInstance();

		// show result
		resultsTable = new JTable();
		resultsTable.setModel(defaultTableModel);
		resultsTable.setSelectionModel(new ForcedListSelectionModel());

		// scroll option
		scrollPane = new JScrollPane(resultsTable);
		panel.add(scrollPane, BorderLayout.CENTER);

		// add small JPanel for Cancel and Confirm buttons
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		for (String title : titles){
			JButton button = new JButton(title);
			panel_1.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					delegate.didPressButton(title, resultsTable.getSelectedRow());
					
				}
			});
		}
	}


	

	
	
	public void insert(historyDataObject e){
		defaultTableModel.addRow(new Object[] {e.toString()});
		//updateClassList();
	}
	
	
	
//	/**
//	 * 
//	 * @return selected data
//	 */
//	public Object selectedData(){
//		int col = 0;
//		int row = resultsTable.getSelectedRow();
//		Object value = resultsTable.getModel().getValueAt(row, col);
//        JFrame f = new JFrame("TableSelection");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(this, BorderLayout.CENTER);
//		btnCancel = new JButton("Cancel");
//		panel_2.add(btnCancel);
//		btnCancel.addActionListener(this);
//        f.add(btnCancel, BorderLayout.SOUTH);
//        f.pack();
//        f.setLocationRelativeTo(null);
//        f.setVisible(true);
//		return value;
//	}
	

	public void setDelegate(ClassSearchFrameDelegateInterface delegate) {
		this.delegate = delegate;
	}

	/*
	 * Forcely remove single selection in Jtable
	 */
	private static class ForcedListSelectionModel
			extends
				DefaultListSelectionModel {
		public ForcedListSelectionModel() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		};
		/*
		 * potentially better solve the selection problem, but not needed at the
		 * moment
		 * 
		 * @Override public void clearSelection() { }
		 * 
		 * @Override public void removeSelectionInterval(int index0, int index1)
		 * { }
		 */
	}
}
