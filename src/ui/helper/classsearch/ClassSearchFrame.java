package ui.helper.classsearch;

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

public class ClassSearchFrame extends JFrame
		implements
			ActionListener,
			DocumentListener {

	private JTextField searchingTextField;
	private JTable resultsTable;
	private ClassSearch searchUtil;
	private DefaultTableModel defaultTableModel;
	private ClassSearchFrameDelegateInterface delegate;

	private Thread runningThread;
	JButton btnCancel, btnConfirm;
	JPanel panel;
	JScrollPane scrollPane;

	/*
	 * Setup class finders
	 */
	public ClassSearchFrame() {

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
		searchUtil = ClassSearch.sharedInstance();
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

	private void updateClassList() {
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
				ArrayList<String> classesForName = searchUtil
						.classesForName(searchText);

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

	/**
	 * perform cancel and confirm action
	 */
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
				delegate.didSelectClass(
						(String) resultsTable.getValueAt(selectedRow, 0));

			}

			setVisible(false);
			dispose();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

		updateClassList();
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateClassList();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

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
