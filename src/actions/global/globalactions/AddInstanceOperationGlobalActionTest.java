package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

public class AddInstanceOperationGlobalActionTest {
	
	/**
	 * Add an instance operation component:
	 * String object, using the first constructor
	 */
	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		
		AddInstanceOperationGlobalAction action = new AddInstanceOperationGlobalAction();
		
		assertEquals(0, panel.getPaintComponents().size());
		
		//use the first constructor of String as example
		action.setConstructorToSet(String.class.getConstructors()[0]);
		
		action.execute(panel);
		
		assertEquals(1, panel.getPaintComponents().size());
		
		assertTrue(panel.getPaintComponents().get(0) instanceof InstanceOperationComponent);
	}

}
