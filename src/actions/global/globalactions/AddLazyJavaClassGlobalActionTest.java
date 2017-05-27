package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

/**
 * AddLazyJavaConstructorClassGlobalActionTest. Author: Xiaoquan Jiang Test
 * AddLazyJavaConstructorClassGlobalAction
 */
public class AddLazyJavaClassGlobalActionTest {

	/**
	 * Test the number of components on the panel and the type of component
	 * added to verify adding a component
	 */
	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		AddLazyJavaClassGlobalAction assiciatedAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssociatedAction();
		assiciatedAction.setClassToCreate("string".getClass());

		assertEquals("test number of components before", 0, panel
				.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
		assertEquals("test number of components before", 1, panel
				.getPaintComponents().size());
		assertTrue("test type of component added", panel.getPaintComponents()
				.get(0) instanceof ClassPaintComponent);
	}
}
