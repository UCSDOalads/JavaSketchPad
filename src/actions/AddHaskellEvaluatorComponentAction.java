package actions;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddHaskellEvaluatorComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

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
		InputManager im = new InputManager();
		im.askForClass(panel, new InputManagerDelegate<Class>() {

			@Override
			public void didFinishInput(Class input) {
				AddHaskellEvaluatorComponentGlobalAction assiciatedAction = (AddHaskellEvaluatorComponentGlobalAction) ActionName.ADD_HASKELL_EVALUATOR_COMPONENT
						.getAssiciatedAction();
				assiciatedAction.setClassToCreate(input);
				GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);

			}
		});
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Evaluator").toString();
	}

}
