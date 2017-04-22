package actions;

import javax.swing.JOptionPane;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddHaskellComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;

public class AddHaskellComponent extends MenuBarPaintAction {

	public AddHaskellComponent(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String expr = JOptionPane.showInputDialog("Please enter the haskell expression");
		AddHaskellComponentGlobalAction assiciatedAction = (AddHaskellComponentGlobalAction) ActionName.ADD_HASKELL_EXPRESSION_COMPONENT
				.getAssiciatedAction();
		assiciatedAction.setHaskellExpression(expr);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Haskell Expression").toString();
	}

}
