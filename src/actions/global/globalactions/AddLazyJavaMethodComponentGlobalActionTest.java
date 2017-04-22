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
		
		AddLazyJavaMethodComponentGlobalAction action = new AddLazyJavaMethodComponentGlobalAction();
		assertEquals(0, panel.getPaintComponents().size());
		
		//use first method to test
		action.setMethod(String.class.getMethods()[0]);
		action.execute(panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof MethodPaintComponent);
		
	}
}

