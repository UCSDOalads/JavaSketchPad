package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

/**
 * AddLazyJavaFieldsComponentGlobalActionTest. Author: Xiaoquan Jiang Test
 * AddLazyJavaFieldsComponentGlobalAction
 */
public class AddLazyJavaFieldsComponentGlobalActionTest {

	/**
	 * Test the number of components on the panel and the type of component
	 * added to verify adding a component
	 */
	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		// add a class
		AddLazyJavaClassGlobalAction classAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
		classAction.setClassToCreate("string".getClass());
		GlobalPaintActionExecuter.getSharedInstance().execute(classAction,
				panel);

		// set up
		ClassPaintComponent comp = (ClassPaintComponent) panel
				.getPaintComponents().get(0);
		AddLazyJavaFieldsComponentGlobalAction assiciatedAction = (AddLazyJavaFieldsComponentGlobalAction) ActionName.ADD_LAZY_JAVA_FIELDS_ACTION
				.getAssiciatedAction();
		assiciatedAction.setComponent(comp);

		// test
		assertEquals("test number of components before", 1, panel
				.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
		assertEquals("test number of components after", 2, panel
				.getPaintComponents().size());
		assertTrue("test type of component added", panel.getPaintComponents()
				.get(1) instanceof FieldsPaintComponent);
	}

}
