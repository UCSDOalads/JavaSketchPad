package ui.helper.methodinput;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class MethodInputFrame extends JFrame {
	
	private JTextField searchingTextField;
	private JTable resultsTable;
	private JButton btnCancel, btnConfirm;
	private JPanel panel;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultTableModel;
	
	private Class mtdClass;
	private Method[] methods;
	
	
	public MethodInputFrame(Class c) {
		// set the defaultTableModel to non editable by user clicking around
		defaultTableModel = new DefaultTableModel(0, 1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		this.mtdClass = c;
		this.methods = this.mtdClass.getMethods();
		
		// big JPanel window
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		searchingTextField = new JTextField();
		getContentPane().add(searchingTextField, BorderLayout.NORTH);
		searchingTextField.setColumns(10);
		
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
		
		ButtonListener listener = new ButtonListener();
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		btnCancel.addActionListener(listener);

		btnConfirm = new JButton("Confirm");
		panel_1.add(btnConfirm);
		btnConfirm.addActionListener(listener);

		// set up connections
//		searchingTextField.addActionListener(this);
//		searchingTextField.getDocument().addDocumentListener(this);
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
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCancel) {
				
			} else if (e.getSource() == btnConfirm) {
				
			}
		}
		
	}
}
