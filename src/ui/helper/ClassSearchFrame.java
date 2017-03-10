package ui.helper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import classpathutil.ClassSearch;

public class ClassSearchFrame extends JFrame implements ActionListener, DocumentListener {
	private JTextField searchingTextField;
	private JTable resultsTable;
	private ClassSearch searchUtil;
	private DefaultTableModel defaultTableModel;
	private Thread runningThread;
	
	

	public ClassSearchFrame() {
		
		//setting up class finders
		
		defaultTableModel = new DefaultTableModel(0, 1);
		searchUtil = ClassSearch.sharedInstance();


		
	

		
		searchingTextField = new JTextField();
		getContentPane().add(searchingTextField, BorderLayout.NORTH);
		searchingTextField.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		resultsTable = new JTable();
		//panel.add(resultsTable, BorderLayout.CENTER);
		
		resultsTable.setModel(defaultTableModel);
		
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		//setting up connections
		
		searchingTextField.addActionListener(this);
		
		searchingTextField.getDocument().addDocumentListener(this);
	}
	

	


	private void updateClassList() {
		if(runningThread!= null){
			runningThread.interrupt();
		}
		runningThread = new Thread( new Runnable() {
			
			@Override
			public void run() {

				String searchText;
				synchronized (searchingTextField) {
					searchText = searchingTextField.getText();
				}
				System.out.println("Searching for " + searchText);
				//start Search
				ArrayList<String> classesForName = searchUtil.classesForName(searchText);
				

				boolean equals;
				synchronized (searchingTextField) {
					equals = 
					//if text has not changed
					searchingTextField.getText().equals(searchText);
				}
				
				if(equals){
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
				
				
			}
		});
		runningThread.start();
	}

	//this method is called when enter key is hit
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

}
