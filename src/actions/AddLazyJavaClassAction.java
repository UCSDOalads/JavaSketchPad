package actions;

import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaClassAction extends AddComponentActions {

	public AddLazyJavaClassAction(PaintPanel panel) {
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
				AddLazyJavaClassGlobalAction associatedAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
						.getAssociatedAction();
				associatedAction.setClassToCreate(input);
				associatedAction.setCoord(x, y);
				GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);

			}
		});

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
