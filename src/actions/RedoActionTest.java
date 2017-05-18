package actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;

/**
 * RedoActionTest
 * 
 * @author DYB Test the functionality of redo
 */
public class RedoActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		// perform one action
		AddLazyJavaClassGlobalAction action = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
		action.setClassToCreate(this.getClass());
		GlobalPaintActionExecuter.getSharedInstance().execute(action, panel);

		// new redo action
		RedoAction action1 = new RedoAction(panel);

		// determine whether can redo or not
		assertFalse(action1.canPerformAction());
		// do an undo action
		
		new UndoAction(panel).performAction();
		// can redo now
		assertTrue(action1.canPerformAction());
		// do a redo
		action1.performAction();
		// cannot redo
		assertFalse(action1.canPerformAction());
		

	}

}
