package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.InstanceOperationComponent;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;

public class ExecuteInstanceMethodGlobalAction extends GlobalPaintAction {



	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		doExecute(panel);
	
	}
	
	
	protected Object doExecute(PaintPanel panel){
		

		MethodPaintComponent methodComp = 
				(MethodPaintComponent)panel.getSelectTool().
				getSelectedComponents().get(0);
		return methodComp.executeMethod();

	}
	


	
}
