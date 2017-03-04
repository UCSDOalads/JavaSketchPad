package actions;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;

public class AddDataDisplayBoxAction extends PaintAction {

	public AddDataDisplayBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		DataDisplayPaintComponent comp = new DataDisplayPaintComponent("Data Display", panel.getWidth() /2, panel.getHeight()/2);
		panel.addPaintComponent(comp);
		
		//push action to manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				panel.getPaintComponents().remove(comp);
			}
			
			@Override
			public void redoAction() {
				panel.addPaintComponent(comp);

			}
		});
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Display_Box().Add().toString();
	}

}
