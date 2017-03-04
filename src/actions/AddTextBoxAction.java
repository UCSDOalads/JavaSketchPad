package actions;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

public class AddTextBoxAction extends PaintAction {

	public AddTextBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String s = JOptionPane.showInputDialog("Please enter the text to display");
		TextPaintComponent comp = new TextPaintComponent(s, panel.getWidth() / 2, panel.getHeight()/2);
		panel.addPaintComponent(comp);
		//push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				comp.remove(panel);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				panel.addPaintComponent(comp);
				panel.repaint();
			}
		});
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Add/Text Box...").toString();
	}

}
