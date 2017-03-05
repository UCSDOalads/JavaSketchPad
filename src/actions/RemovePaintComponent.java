package actions;

import paintcomponents.PaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;

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
		for ( PaintComponent comp: panel.getSelectTool().getSelectedComponents())	{
			comp.remove( panel );
		}
		
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
