package actions.global.globalactions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

/**
 * EditUndoActionTest
 * 
 * @author cs30xsd Test the functionality of global undo
 */
public class EditUndoActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		// perform one action
		AddLazyJavaClassGlobalAction action = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssociatedAction();
		action.setClassToCreate(this.getClass());
		GlobalPaintActionExecuter.getSharedInstance().execute(action, panel);

		// new undo action
		EditRedoGlobalAction action1 = new EditRedoGlobalAction();
		EditUndoGlobalAction action2 = new EditUndoGlobalAction();

		assertEquals(1, panel.getPaintComponents().size());
		// perform one undo action
		action2.execute(panel);
		assertEquals(0, panel.getPaintComponents().size());
		action1.execute(panel);
		assertEquals(1, panel.getPaintComponents().size());
		action2.execute(panel);
		assertEquals(0, panel.getPaintComponents().size());
	}

}
