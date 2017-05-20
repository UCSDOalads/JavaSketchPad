package actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;

/**
 * UndoActionTest
 * 
 * @author DYB Test the functionality of undo
 */
public class UndoActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		// perform one action
		AddLazyJavaClassGlobalAction action = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
		action.setClassToCreate(this.getClass());
		GlobalPaintActionExecuter.getSharedInstance().execute(action, panel);

		// new undo action
		UndoAction action1 = new UndoAction(panel);
		// determine whether can undo or not
		assertTrue(action1.canPerformAction());
		// perform one undo action
		action1.performAction();
		// determine again whether can undo or not
		assertFalse(action1.canPerformAction());

	}

}
