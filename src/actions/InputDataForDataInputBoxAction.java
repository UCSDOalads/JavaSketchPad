package actions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class InputDataForDataInputBoxAction extends MenuBarPaintAction {

	public InputDataForDataInputBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		ArrayList<PaintComponent> comps = panel.getSelectTool().getSelectedComponents();
		if(comps.size()!= 1) return false;
		if(comps.get(0) instanceof DataInputTextfieldPaintComponent){
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		DataInputTextfieldPaintComponent inputComp = (DataInputTextfieldPaintComponent) panel.getSelectTool().getSelectedComponents().get(0);
		String s = JOptionPane.showInputDialog("Please specify the message to push to the data input");
		String original = inputComp.getDisplayingText();
		inputComp.inputData(s);
		// add action to undo redo manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				inputComp.inputData(original);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				inputComp.inputData(s);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "update inputBox";
			}

			@Override
			protected String commandDescription() {
				return "Input a string into the text input box";
			}
		});
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Input_Box().Update().toString();
	}

}
