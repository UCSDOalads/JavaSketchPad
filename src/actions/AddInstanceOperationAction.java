package actions;

import java.lang.reflect.Constructor;

import javax.swing.JOptionPane;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddInstanceOperationGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;


public class AddInstanceOperationAction extends MenuBarPaintAction {

	
	public AddInstanceOperationAction(PaintPanel panel) {
		super(panel);
	}

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
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);
		Constructor[] cons = comp.getDisplayingClass().getConstructors();
		
		String desiaredConstructorIndex = JOptionPane.showInputDialog(
						"Please enter the index of the constructor you would like to use: \n\n\n"
								+ getConstructorsSelectionUI(cons));
		//call inputChecker to check if input is valid
		DialogInputChecker inputChecker = new DialogInputChecker();
		if(inputChecker.isValidNumber(desiaredConstructorIndex, 0, cons.length -1)){
			AddInstanceOperationGlobalAction assiciatedAction = 
					(AddInstanceOperationGlobalAction) ActionName.ADD_INSTANCE_OPERATION_ACTION
					.getAssiciatedAction();
			assiciatedAction.setConstructorToSet(cons[Integer.parseInt(desiaredConstructorIndex)]);
			GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);
		}
		
	}
	
	public String getConstructorsSelectionUI(Constructor[] cons) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cons.length; i++) {
			Constructor constructor = cons[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();

	}
	
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Instance_Operation().toString();
	}
	
	
}
