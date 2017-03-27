package actions;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import painttools.tools.SmartTool;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import actions.menu.ActionsPopupMenuTitles;

public class RemovePaintComponent extends PaintAction{

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
			}
			@Override
			public void redoAction() {
				for( PaintComponent comp: comps ) 
					comp.remove(panel);
			}
		});

		panel.repaint();
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		if(panel.getSelectTool() instanceof SmartTool)
			return ActionsPopupMenuTitles.Remove().toString();
		return ActionsMenuBarTitles.Data().Remove().toString();
	}
	
}
