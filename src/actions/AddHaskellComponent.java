package actions;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddHaskellComponentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

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
		InputManager im = new InputManager();
		im.askForClass(panel, new InputManagerDelegate<Class>() {

			@Override
			public void didFinishInput(Class input) {
				AddHaskellComponentGlobalAction assiciatedAction = (AddHaskellComponentGlobalAction) ActionName.ADD_HASKELL_EXPRESSION_COMPONENT
						.getAssiciatedAction();
				assiciatedAction.setClassToCreate(input);
				GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);

			}
		});

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Haskell Expression")
				.toString();
	}

}
