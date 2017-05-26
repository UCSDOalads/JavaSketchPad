package actions;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddDataDisplayBoxGlobalAction;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class AddDataDisplayBoxAction extends AddComponentActions {

	public AddDataDisplayBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		
		return true;
	}

	@Override
	public void performAction() {

		AddDataDisplayBoxGlobalAction assiciatedAction = (AddDataDisplayBoxGlobalAction) ActionName.ADD_DATA_DISPLAY_BOX
				.getAssiciatedAction();
		assiciatedAction.setDisplayString("Data Display");
		assiciatedAction.setX(x);
		assiciatedAction.setY(y);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Display_Box().Add().toString();
	}

}
