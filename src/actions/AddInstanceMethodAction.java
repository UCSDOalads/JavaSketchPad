package actions;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddInstanceMethodGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

/**
 * These two are instance methods (Together not seperate) for operation.
 * 
 * @author chenzb
 *
 */
public class AddInstanceMethodAction extends MenuBarPaintAction {

	public AddInstanceMethodAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof InstanceOperationComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		InstanceOperationComponent insComp = 
				(InstanceOperationComponent)panel.getSelectTool().
				getSelectedComponents().get(0);
		Method[] methods = insComp.getDisplayingClass().getMethods();
		
		String desiaredConstructorIndex = JOptionPane.showInputDialog(
						"Please enter the index of the constructor you would like to use: \n\n\n"
								+ getMethodsSelectionUI(methods));
		
		//call DialogInputChecker to check input
		DialogInputChecker inputChecker = new DialogInputChecker();
		if(inputChecker.isValidNumber(desiaredConstructorIndex, 0, methods.length -1)){
			AddInstanceMethodGlobalAction associatedAction = 
					(AddInstanceMethodGlobalAction) ActionName.ADD_INSTANCE_METHOD_ACTION
					.getAssociatedAction();
			associatedAction.setInsComp(insComp);
			associatedAction.setMethodToSet(methods[Integer.parseInt(desiaredConstructorIndex)]);
			GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
		}
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Lazy().Add().Add_Instance_Method().toString();
	}
	
	public String getMethodsSelectionUI(Method[] methods) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < methods.length; i++) {
			Method constructor = methods[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();

	}
	
}
