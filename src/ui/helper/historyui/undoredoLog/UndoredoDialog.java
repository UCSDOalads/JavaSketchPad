package ui.helper.historyui.undoredoLog;



import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;


import javax.swing.JButton;
import javax.swing.undo.UndoManager;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.SharedUndoRedoActionManagerDelegate;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.helper.historyui.HistoryDataObject;
import ui.helper.historyui.HistoryUI;
import ui.helper.historyui.HistoryUIInterface;

public class UndoredoDialog extends HistoryUI implements HistoryUIInterface{
	
	private static final String BUTTON_TITLE_REDO = "Redo";
	private static final String BUTTON_TITLE_UNDO = "Undo";
	SharedUndoRedoActionManager undoRedoManager;
	
	private UndoredoDialog(String[] titles) {
		super(titles);
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

	static UndoredoDialog instance = new UndoredoDialog(new String[]{BUTTON_TITLE_UNDO, BUTTON_TITLE_REDO});
	
	public static UndoredoDialog sharedInstance(){
		return instance;
	}
	
	
	private void update(){
		clear();
		Stack<UndoRedoableInterface> undoStack = undoRedoManager.getUndoStack();
		
		for(int i = 0; i < undoStack.size(); i ++){
			insert(new HistoryDataObject(undoStack.get(i).description()));
		}
		
		
		Stack<UndoRedoableInterface> redoStack = undoRedoManager.getRedoStack();
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
