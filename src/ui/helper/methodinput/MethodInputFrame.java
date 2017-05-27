package ui.helper.methodinput;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * similar implementation as class search frame
 */
public class MethodInputFrame extends JFrame implements ActionListener, DocumentListener {
	
	private JTextField searchingTextField;
	private JTable resultsTable;
	private MethodSearch searchUtil;
	private DefaultTableModel defaultTableModel;
	private MethodSearchFrameDelegateInterface delegate;

	private Thread runningThread;
	private JButton btnCancel, btnConfirm;
	private JPanel panel;
	private JScrollPane scrollPane;
	
	
	public MethodInputFrame(Class c) {
		// set the defaultTableModel to non editable by user clicking around
		defaultTableModel = new DefaultTableModel(0, 1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// big JPanel window
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// search text field on top
		searchUtil = new MethodSearch(c);
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

		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		btnCancel.addActionListener(this);

		btnConfirm = new JButton("Confirm");
		panel_1.add(btnConfirm);
		btnConfirm.addActionListener(this);

		// set up connections
		searchingTextField.addActionListener(this);
		searchingTextField.getDocument().addDocumentListener(this);
		
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
	
	private void updateMethodList() {
		if (runningThread != null) {
			runningThread.interrupt();
		}
		runningThread = new Thread(new Runnable() {

			@Override
			public void run() {

				String searchText;
				synchronized (searchingTextField) {
					searchText = searchingTextField.getText();
				}
				// start Search
				ArrayList<String> classesForName = searchUtil.methodsForName(searchText);

				boolean equals;
				synchronized (searchingTextField) {
					equals =
							// if text has not changed
							searchingTextField.getText().equals(searchText);
				}

				if (equals) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							defaultTableModel.setRowCount(0);
							for (String string : classesForName) {
								defaultTableModel.addRow(new String[]{string});
							}
							defaultTableModel.fireTableDataChanged();
						}
					});
				}

				// It is buggy with the synchronized searching
				// resultsTable.setRowSelectionInterval(0, 0);
			}
		});
		runningThread.start();
	}
	
	public void setDelegate(MethodSearchFrameDelegateInterface delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateMethodList();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateMethodList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// User cancel the class search
		
		if (e.getSource() == btnCancel) {
			setVisible(false);
			dispose();
		} else if (e.getSource() == btnConfirm) {

			// return the selected (or the first if no selected) class if the
			// table is not empty
			if (resultsTable.getRowCount() != 0) {

				int selectedRow = resultsTable.getSelectedRow();
				if (selectedRow == -1) {
					selectedRow = 0;
				}
				// TODO get selected/highlighted class, not just the first one
				delegate.didSelectMethod(
						(String) resultsTable.getValueAt(selectedRow, 0));

			}

			setVisible(false);
			dispose();
		}
	}
}
