package ui.helper.historyui.undoredoLog;



import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;


import javax.swing.JButton;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.helper.historyui.HistoryDataObject;
import ui.helper.historyui.HistoryUI;

public class UndoredoLog extends HistoryUI{
	
	private static final long serialVersionUID = -8533431925180978587L;
	SharedUndoRedoActionManager undoRedoManager;
	Stack<Integer> num_undo;
	
	private UndoredoLog(String[] titles) {
		super(titles);
		num_undo = new Stack<>();
		// TODO Auto-generated constructor stub
	}

	static UndoredoLog instance = new UndoredoLog(new String[]{"undo", "redo"});
	
	public static UndoredoLog sharedInstance(){
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		
		//if redo button pressed
		if(btn.getText().equals("redo")){
			redo();
		}
		//if undo button pressed
		else if(btn.getText().equals("undo")){
				int row = getResultsTable().getSelectedRow() + 1;
				int[] indices = IntStream.range(row, getDefaultTableModel().getRowCount()).toArray();
				removeSeveralRows(indices);;
				//TODO
		}

		//if exit button pressed, close window
		else if(btn.getText().equals("exit")){
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	/**
	 * remove several selected rows from table and stores them into stack
	 * param: an array of rows number to be removed
	 */
	@Override
	public void removeSeveralRows(int[] indices){
		Arrays.sort(indices);
		HistoryDataObject[] objects = new HistoryDataObject[indices.length];
		for(int i= 0; i< indices.length; i++){
			HistoryDataObject object = (HistoryDataObject) getResultsTable().getModel().getValueAt(indices[i], 0);
			if(object.getUntraced())
				break;
			//undoRedoManager.pushUndoableAction((UndoRedoableInterface) object);
			object.setUntraced(true);
			objects[i] = object;
		}
		getDefaultTableModel().fireTableDataChanged();
		getDelete_history().push(objects);
	}
	
	/**
	 * 
	 */
	public void redo(){
		if(!getDelete_history().isEmpty()){
			HistoryDataObject[] objects = getDelete_history().pop();
			//insert data to its row
			for(int i = 0; i<objects.length; i++){
				if(objects[i]==null)
					break;
				objects[i].setUntraced(false);	
			}
			getDefaultTableModel().fireTableDataChanged();
		}
	}
	
}
