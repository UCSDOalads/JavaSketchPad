package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

public class AddLazyJavaClassGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		AddLazyJavaClassGlobalAction assiciatedAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
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
