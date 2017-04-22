package actions;

import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddHaskellEvaluatorComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddHaskellEvaluatorComponentAction extends MenuBarPaintAction {

	public AddHaskellEvaluatorComponentAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		AddHaskellEvaluatorComponentGlobalAction assiciatedAction = (AddHaskellEvaluatorComponentGlobalAction) ActionName.ADD_HASKELL_EVALUATOR_COMPONENT
						.getAssiciatedAction();
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Evaluator").toString();
	}

}
