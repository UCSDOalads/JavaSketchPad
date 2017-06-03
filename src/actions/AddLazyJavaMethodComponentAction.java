package actions;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import ui.helper.methodinput.MethodInputFrame;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.AddLazyJavaMethodComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaMethodComponentAction extends MenuBarPaintAction {

	public AddLazyJavaMethodComponentAction(PaintPanel panel) {
		super(panel);
	}

	//TODO
	//NOTE: I am copying from Constructor, consider refinement
	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof ClassPaintComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool().getSelectedComponents().get(0);
		
		InputManager im = new InputManager();
		im.askForMethod(panel, new InputManagerDelegate<Method>() {

			@Override
			public void didFinishInput(Method input) {
				AddLazyJavaMethodComponentGlobalAction assiciatedAction = (AddLazyJavaMethodComponentGlobalAction) 
						ActionName.ADD_LAZY_JAVA_METHOD_ACTION.getAssiciatedAction();
				assiciatedAction.setMethodComponent(comp);
				assiciatedAction.setMethod(input);
				GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);
			}
		}, comp.getDisplayingClass());
	}
		
	public String getMethodsSelectionUI(Method[] methods) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < methods.length; i++) {
			Method constructor = methods[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();

	}
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Method().toString();
	}

}
