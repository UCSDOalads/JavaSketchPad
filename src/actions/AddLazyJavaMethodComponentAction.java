package actions;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.AddLazyJavaMethodComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

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
		Method[] methods = comp.getDisplayingClass().getMethods();

		String desiaredConstructorIndex = JOptionPane.showInputDialog(
						"Please enter the index of the constructor you would like to use: \n\n\n"
								+ getMethodsSelectionUI(methods));
		//call DialogInputChecker to check input
		DialogInputChecker inputChecker = new DialogInputChecker();
		if(inputChecker.isValidNumber(desiaredConstructorIndex, 0, methods.length -1)){
			AddLazyJavaMethodComponentGlobalAction assiciatedAction 
			= (AddLazyJavaMethodComponentGlobalAction) ActionName.ADD_LAZY_JAVA_METHOD_ACTION.getAssiciatedAction();
			assiciatedAction.setMethodComponent(comp);
			assiciatedAction.setMethod(comp.getDisplayingClass().getMethods()[Integer.parseInt(desiaredConstructorIndex)]);
			GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);
		}

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
