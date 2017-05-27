package ui.helper.historyui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import buttons.CustomJButton;


/**
 * create historyUI
 * 
 * if you want to change the current HistoryUI design in subclass,
 * 	use getter and setter
 *
 */
public class HistoryUI extends JPanel {
	
	private JTable resultsTable;
	private DefaultTableModel defaultTableModel;
	private JScrollPane scrollPane;
	private JPanel button_panel;
	private ArrayList<JButton> buttonArray = new ArrayList<JButton>();
	private Color color = new Color(150,150,150);
	protected HistoryUIInterface delegate;
	private int index = -1; //value of last item that is not untraced

	/**
	 * Setup historyUI
	 */
	public HistoryUI(String[] titles){

		// set the defaultTableModel to non editable by user clicking around
		this.setDefaultTableModel((new DefaultTableModel(0, 1) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}));
		

		setLayout(new BorderLayout(0, 0));
	    
	    //set size
	    setPreferredSize(new Dimension(160, 300));
	    
	    //set table
	    setTable();
	    
	    //set table selectionMode
	    setSelectionMode();
	    
	    //create a scrollPane for table
	    setScrollPane();
	    
	    //create panel for buttons
	    setButtonPanel();
		
		setForeground(getColor());
		setBackground(getColor());
	}


	protected void setTable() {
		this.setResultsTable((new JTable()));
		getResultsTable().setTableHeader(null);
		getResultsTable().setFont(new Font("Apple LiSung", Font.PLAIN, 16));
		getResultsTable().setShowHorizontalLines(false);
		getResultsTable().setShowVerticalLines(false);
		getResultsTable().setShowGrid(false);
		getResultsTable().setBorder(null);
		getResultsTable().setBackground(getColor());
		getResultsTable().setForeground(Color.WHITE);
		getResultsTable().setModel(getDefaultTableModel());
	}
	
	/**
	 * add a button to button_panel of History UI
	 * @param b button to be added
	 */
	protected void addButtons(CustomJButton b){
		getButton_panel().add(b);
		getButtonArray().add(b);
		//getButton_panel().repaint();
	}
	

	protected void setButtons(String[] titles){
		// add small JPanel for buttons


		//loop through arr and add buttons
		for (String title : titles){
			JButton button = new JButton(title);
			getButton_panel().add(button);
			getButtonArray().add(button);
			button.setEnabled(false);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					delegate.didPressButton(title, getResultsTable().getSelectedRow());
				}
			});
		}
	}

	
	protected void setSelectionMode(){
		getResultsTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void setScrollPane(){
		// scroll option
		scrollPane = new JScrollPane(getResultsTable());
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setBackground(getColor());
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(scrollPane, BorderLayout.CENTER);
	}

	private void setButtonPanel() {
		FlowLayout fl_button_panel = new FlowLayout();
		setButton_panel(new JPanel(fl_button_panel));
	    getButton_panel().setBorder(BorderFactory.createLineBorder(Color.black));
		getButton_panel().setBackground(getColor());
		add(getButton_panel(), BorderLayout.SOUTH);
	}

	/**
	 * set redo for last row, last col
	 */
	public void redo(){
		redo(++index, getNumCol() - 1);
	}
	
	/**
	 * set redo for specific row
	 */
	public void redo(int r,int c){
		HistoryDataObject o = (HistoryDataObject) getDefaultTableModel().getValueAt(r,c );
		o.setUntraced(false);
		getDefaultTableModel().fireTableDataChanged();
	}
	
	/**
	 * set undo for last row, last col
	 */
	public void undo(){
		 undo(index, getNumCol() - 1 );
	}
	
	/**
	 * set undo for specific row
	 * @param r
	 */
	public void undo(int r, int c){
		HistoryDataObject o = (HistoryDataObject) getDefaultTableModel().getValueAt(r, c);
		o.setUntraced(true);
		getDefaultTableModel().fireTableDataChanged();
		index--;
	}
	
	/**
	 * add a row to the end of table
	 * receive a HistoryDataObject
	 */
	public void insert(HistoryDataObject e){
		clearUntraced();
		int r = getNumRow();
		insert(e,r);
	}
	
	/**
	 * insert a row into specific row
	 */
	public void insert(HistoryDataObject e, int r){
		clearUntraced();
		getDefaultTableModel().insertRow(r,new Object[] {e});
		getDefaultTableModel().fireTableDataChanged();
		updateIndex(getNumRow() - 1);
		
	}
	
	/**
	 * remove a row from the table
	 */
	public void remove(int r){
		getDefaultTableModel().removeRow(r);
		getDefaultTableModel().fireTableDataChanged();
		updateIndex(getNumRow() - 1);
	}
	
	/**
	 * remove the last row from the table
	 */
	public void remove(){
		int r = getNumRow() - 1;
		remove(r);
	}
	
	
	/**
	 * remove all rows from row n to the end of table
	 * if n is greater than or equals to number of rows in table,
	 * 		then nothing will be changed
	 * 
	 * n start at 0.
	 * @param n
	 */
	public void removeContinuousRowStartFrom(int n){
		//clear table
		int numRow = getNumRow();
		if( n < numRow){
			for(int i = n; i < numRow; i++ ){
				remove(n);
			}
		}
	}

	/**
	 * remove several selected rows from table
	 * @param: an array of rows number to be removed
	 */
	public void removeSeveralRows(int[] indices){
		Arrays.sort(indices);
		int numRow = getNumRow();
		//loop through indices and delete
		for(int i = 0; i < indices.length; i++){
			if( i < numRow){
				remove(indices[i]);
			}
		}
	}
	
	/**
	 * remove all untraced item from table
	 */
	public void clearUntraced(){
		removeContinuousRowStartFrom(index+1);
	}
	
	/**
	 * remove all rows from table
	 */
	public void clear(){
		int rowCount = getNumRow();
		for(int i = 0; i < rowCount; i++){
			getDefaultTableModel().removeRow(0);
		}
		getDefaultTableModel().fireTableDataChanged();
	}

	public void setDelegate(HistoryUIInterface delegate) {
		this.delegate = delegate;
	}

	
	/**
	 * Getting the array of buttons available
	 */
	public ArrayList<JButton> getButtonArray() {
		return buttonArray;
	}
	
	public void refresh(){
		defaultTableModel.fireTableDataChanged();
	}
	/**
	 * return the number of rows in table now
	 */
	protected int getNumRow(){
		return getDefaultTableModel().getRowCount();
	}

	/**
	 * return number of column in table now
	 * @return
	 */
	protected int getNumCol(){
		return getDefaultTableModel().getColumnCount();
	}

	/**
	 * 
	 * @param i
	 */
	private void updateIndex(int i){
		index = i;
	}
	
	/**
	 * index will be the value of last item that is not untraced
	 * @return
	 */
	public int getIndex(){
		return index;
	}
	
	public JTable getResultsTable() {
		return resultsTable;
	}


	public void setResultsTable(JTable resultsTable) {
		this.resultsTable = resultsTable;
		
	}


	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}


	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JPanel getButton_panel() {
		return button_panel;
	}


	public void setButton_panel(JPanel button_panel) {
		this.button_panel = button_panel;
	}


	public void setButtonArray(ArrayList<JButton> buttonArray) {
		this.buttonArray = buttonArray;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
}

