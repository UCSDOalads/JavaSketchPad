package actions;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;

public class RemovePaintComponent extends MenuBarPaintAction{

	public RemovePaintComponent(PaintPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void performAction() {
		ArrayList<PaintComponent> comps = new ArrayList<>();
		for ( PaintComponent comp: panel.getSelectTool().getSelectedComponents())	{
			comps.add(comp);
		}


		for( PaintComponent comp: comps ) comp.remove(panel);
		
		//push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
				
			@Override
			public void undoAction() {
				for( PaintComponent comp: comps )
					panel.addPaintComponent(comp);
				panel.repaint();
				
			}
			@Override
			public void redoAction() {
				for( PaintComponent comp: comps ) 
					comp.remove(panel);
				panel.repaint();
			}
			@Override
			protected String commandName() {
				return "remove";
			}
			@Override
			protected String commandDescription() {
				return "removes a paint component";
			}
		});

		panel.repaint();
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Data().Remove().toString();	}
	
	public String toString() {
		return "Remove";
	}
}
