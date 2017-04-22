package actions;

import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaClassAction extends MenuBarPaintAction {

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
				AddLazyJavaClassGlobalAction assiciatedAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
						.getAssiciatedAction();
				assiciatedAction.setClassToCreate(input);
<<<<<<< HEAD
=======
				assiciatedAction.setCoord(panel.getWidth() / 2,
						panel.getHeight() / 2);
>>>>>>> develop
				GlobalPaintActionExecuter.getSharedInstance().execute(
						assiciatedAction, panel);

			}
		});


	}
	

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
