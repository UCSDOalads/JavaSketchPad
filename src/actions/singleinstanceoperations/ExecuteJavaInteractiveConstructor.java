package actions.singleinstanceoperations;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.ClassConstructorPaintComponent;
import ui.PaintPanel;

public class ExecuteJavaInteractiveConstructor extends SingleInstanceOperation<ClassConstructorPaintComponent> {

	public ExecuteJavaInteractiveConstructor(PaintPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void performActionOnInstance(ClassConstructorPaintComponent instance) {
		
		instance.evaluate();
	}

	@Override
	protected Class<ClassConstructorPaintComponent> getGenericClassType() {
		return ClassConstructorPaintComponent.class;
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Developer("Interactive/Constructor Evaluate").toString();
	}

}
