package actions;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;

public class ExecuteInstanceMethodAction extends PaintAction {

	public ExecuteInstanceMethodAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof MethodPaintComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		MethodPaintComponent methodComp = 
				(MethodPaintComponent)panel.getSelectTool().
				getSelectedComponents().get(0);
		methodComp.executeMethod();
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Lazy().Add().Execute_Instance_Method().toString();
	}
	
	
	
}
