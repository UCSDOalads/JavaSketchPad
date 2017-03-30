package actions.singleinstanceoperations;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.ClassConstructorPaintComponent;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;

public class ExecuteJavaInteractiveMethod extends SingleInstanceOperation<MethodPaintComponent>{

	public ExecuteJavaInteractiveMethod(PaintPanel panel) {
		super(panel);
	}

	@Override
	protected void performActionOnInstance(MethodPaintComponent instance) {
		// TODO Auto-generated method stub
		instance.evaluate();
	}

	@Override
	protected Class<MethodPaintComponent> getGenericClassType() {
		// TODO Auto-generated method stub
		return MethodPaintComponent.class;
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Developer("Interactive/Method Evaluate").toString();
	}

}
