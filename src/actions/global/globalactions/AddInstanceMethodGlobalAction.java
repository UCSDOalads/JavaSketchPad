package actions.global.globalactions;

import java.lang.reflect.Method;

import actions.global.GlobalPaintAction;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

public class AddInstanceMethodGlobalAction extends GlobalPaintAction {
	
	private InstanceOperationComponent insComp;
	private Method methodToSet;
	
	@Override
	protected void execute(PaintPanel panel) {
		insComp.addMethodPaintComponent(methodToSet, panel);
		panel.repaint();
	}
	
	public void setInsComp(InstanceOperationComponent insComp) {
		this.insComp = insComp;
	}

	public void setMethodToSet(Method methodToSet) {
		this.methodToSet = methodToSet;
	}

}
