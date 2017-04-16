package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

public class AddLazyJavaMethodComponentGlobalActionTest {

	@Test
	public void test() {
		
		PaintPanel panel = new PaintPanel();
		
		AddLazyJavaClassGlobalAction classAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
		.getAssiciatedAction();
		classAction.setClassToCreate("string".getClass());
		GlobalPaintActionExecuter.getSharedInstance().execute(classAction, panel);
		
		ClassPaintComponent comp = (ClassPaintComponent) panel.getPaintComponents().get(0);
		AddLazyJavaMethodComponentGlobalAction assiciatedAction = (AddLazyJavaMethodComponentGlobalAction) ActionName.ADD_LAZY_JAVA_METHOD_ACTION.getAssiciatedAction();
		assiciatedAction.setMethodComponent(comp);
		
		assertEquals(1, panel.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
		panel);
		assertEquals(2, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(1) instanceof MethodPaintComponent);
	}
}

