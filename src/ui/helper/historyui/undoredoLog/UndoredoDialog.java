package ui.helper.historyui.undoredoLog;



import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.SharedUndoRedoActionManagerDelegate;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.helper.historyui.HistoryDataObject;
import ui.helper.historyui.HistoryUI;
import ui.helper.historyui.HistoryUIInterface;

public class UndoredoDialog extends HistoryUI implements HistoryUIInterface{
	
	private static final String BUTTON_TITLE_REDO = "Redo";
	private static final String BUTTON_TITLE_UNDO = "Undo";
	private ArrayList<JButton> buttonArray;
	private static final int UNDO_INDEX = 0;
	private static final int REDO_INDEX = 1;
	SharedUndoRedoActionManager undoRedoManager;
	
	private UndoredoDialog(String[] titles) {
		super(titles);

		buttonArray = super.getButtonArray();
		setDelegate(this);
		
		undoRedoManager = SharedUndoRedoActionManager.getSharedInstance();
		undoRedoManager.setDelegate(new SharedUndoRedoActionManagerDelegate() {

			@Override
			public void didUndoAction(UndoRedoableInterface obj) {
				update();
				
				
			}
			
			@Override
			public void didRedoAction(UndoRedoableInterface obj) {
				
				update();
			}

			@Override
			public void didAddNewAction(
					UndoRedoableInterface undoredoableAction) {
				update();
				
			}
		});
	}

	static UndoredoDialog instance = new UndoredoDialog(new String[] { BUTTON_TITLE_UNDO, BUTTON_TITLE_REDO });
	
	public static UndoredoDialog sharedInstance(){
		return instance;
	}
	
	
	private void update(){
		clear();
		Stack<UndoRedoableInterface> undoStack = undoRedoManager.getUndoStack();
		
		if (undoStack.size() != 0) {
			buttonArray.get(UNDO_INDEX).setEnabled(true);
		}
		for(int i = 0; i < undoStack.size(); i ++){
			insert(new HistoryDataObject(undoStack.get(i).description()));
		}
		
		
		Stack<UndoRedoableInterface> redoStack = undoRedoManager.getRedoStack();

		if (redoStack.size() != 0) {
			buttonArray.get(REDO_INDEX).setEnabled(true);
		}
		else {
			buttonArray.get(REDO_INDEX).setEnabled(false);
		}
		for(int i = redoStack.size() - 1 ; i >= 0; i --){
			HistoryDataObject historyDataObject = new HistoryDataObject(redoStack.get(i).description());
			historyDataObject.setUntraced(true);
			insert(historyDataObject);
		}
		
		
		
	}


	@Override
	public void didPressButton(String buttonName, int selectedRow) {
		switch (buttonName) {
		case BUTTON_TITLE_UNDO:
			if(selectedRow == -1){
				undoRedoManager.undo();
				if (!undoRedoManager.canUndo()) {
					buttonArray.get(UNDO_INDEX).setEnabled(false);
				}
				buttonArray.get(REDO_INDEX).setEnabled(true);
			} else {
				//undo until reached this action
				if(selectedRow < undoRedoManager.getUndoStack().size() ){
					for (int i = 0; i < undoRedoManager.getUndoStack().size() - selectedRow + 1; i++) {
						undoRedoManager.undo();
					}
				}
			}
			break;
			
		case BUTTON_TITLE_REDO:
			if (selectedRow == -1 ){
				undoRedoManager.redo();
				if (!undoRedoManager.canRedo()) {
					buttonArray.get(REDO_INDEX).setEnabled(false);
				}
				buttonArray.get(UNDO_INDEX).setEnabled(true);
			} else {
				//if select on redo
				if(selectedRow >= undoRedoManager.getUndoStack().size() ){
					//undo select - size +1 times
					for (int i = 0; i < selectedRow - undoRedoManager.getUndoStack().size() + 2 ; i++) {
						undoRedoManager.redo();
					}
				}	
			}

		default:
			break;
		}
		
		update();
		
	}
	
}
