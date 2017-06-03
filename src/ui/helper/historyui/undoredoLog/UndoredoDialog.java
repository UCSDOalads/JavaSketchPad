package ui.helper.historyui.undoredoLog;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.undo.UndoManager;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.SharedUndoRedoActionManagerDelegate;
import actions.edit.undoredo.UndoRedoableInterface;
import buttons.CustomJButton;
import ui.helper.historyui.TableUIDataObject;
import ui.helper.historyui.TableUITemplate;
import ui.helper.historyui.TableUITemplateInterface;
import ui.icons.CustomIcons;

public class UndoredoDialog extends TableUITemplate implements TableUITemplateInterface{
	
	private static final String BUTTON_TITLE_REDO = "Redo";
	private static final String BUTTON_TITLE_UNDO = "Undo";
	private ArrayList<JButton> buttonArray;
	SharedUndoRedoActionManager undoRedoManager;
	private CustomJButton undoButton;
	private CustomJButton redoButton;
	
	private UndoredoDialog(String[] titles) {
		super(titles);

		buttonArray = super.getButtonArray();
		setDelegate(this);
		addButtons();
		updateButtonStatus();
		undoRedoManager = SharedUndoRedoActionManager.getSharedInstance();
		undoRedoManager.setDelegate(new SharedUndoRedoActionManagerDelegate() {

			@Override
			public void didUndoAction(UndoRedoableInterface obj) {
				undo();
				updateButtonStatus();
			}
			
			@Override
			public void didRedoAction(UndoRedoableInterface obj) {
				redo();
				updateButtonStatus();
			}

			@Override
			public void didAddNewAction(
					UndoRedoableInterface undoredoableAction) {
				insert(new TableUIDataObject(undoRedoManager.undoPeek().description()));
				updateButtonStatus();
			}
			
		});
		getScrollPane().setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.BLACK));
		getButton_panel().setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * add undo and redo button to button panel
	 */
	@Override
	public void addButtons(){
		undoButton = new CustomJButton(BUTTON_TITLE_UNDO);
		undoButton.setIcon(CustomIcons.undo());
		undoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.didPressButton(BUTTON_TITLE_UNDO, getResultsTable().getSelectedRow());
				
			}
		});
		super.addButtons(undoButton);
		
		redoButton = new CustomJButton(BUTTON_TITLE_REDO);
		redoButton.setIcon(CustomIcons.redo());
		redoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.didPressButton(BUTTON_TITLE_REDO, getResultsTable().getSelectedRow());
				
			}
		});
		super.addButtons(redoButton);
	}
	

	static UndoredoDialog instance = new UndoredoDialog(new String[] { BUTTON_TITLE_UNDO, BUTTON_TITLE_REDO });
	
	public static UndoredoDialog sharedInstance(){
		return instance;
	}
	
	@Override
	public void didPressButton(String buttonName, int selectedRow) {
		switch (buttonName) {
		case BUTTON_TITLE_UNDO:
			undoContinuousRow();
			break;
			
		case BUTTON_TITLE_REDO:
			redoContinuousRow();

		default:
			break;
		}
		
	}
	/**
	 * redo continuous row to selectedRow
	 * or redo a row if no row is selected by user
	 */
	public void redoContinuousRow(){
		int selectedRow = getSelectedRow();
		if( getSelectedRow() == -1){
			undoRedoManager.redo();
		}
		else{
			for( int i = getIndex()+1; i <= selectedRow; i++){
				undoRedoManager.redo();
			}
		}
	}
	
	/**
	 * undo continuous row from selectedRow to the end of list
	 * or undo last row if no row is selected by user
	 */
	public void undoContinuousRow(){
		int selectedRow = getSelectedRow();
		if( getSelectedRow() == -1){
			undoRedoManager.undo();
		}
		else{
			for(int i = getIndex(); i >= selectedRow; i--){
				undoRedoManager.undo();
			}
		}
	}
	
	/**
	 * disable undoButton when there is no actions to undo
	 * disable redoButton when there is no actions to redo
	 */
	@Override
	public void updateButtonStatus(){
		if(getIndex() == -1 || getNumRow() == 0){
			undoButton.setEnabled(false);
		}
		else{
			undoButton.setEnabled(true);
		}
		
		if(getIndex() == getNumRow()-1 || getNumRow() == 0){
			redoButton.setEnabled(false);
		}
		else{
			redoButton.setEnabled(true);
		}
	}
}
