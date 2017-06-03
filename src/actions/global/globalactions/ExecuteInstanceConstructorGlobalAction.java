package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.InstanceOperationComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class ExecuteInstanceConstructorGlobalAction extends GlobalPaintAction {
	
	private Class classToCreate;
	
	public void setClassToCreate(Class classToCreate) {
		this.classToCreate = classToCreate;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		
		InstanceOperationComponent insComp = 
				(InstanceOperationComponent)panel.getSelectTool().
				getSelectedComponents().get(0);
		insComp.executeConstructor();
		
	}

}
