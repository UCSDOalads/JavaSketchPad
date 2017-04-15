package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.interactive.InstanceOperationComponent;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;

public class AddInstanceMethodGlobalActionTest {

	@Test
	public void test() {
		
		//first add a instance operation component
		PaintPanel panel = new PaintPanel();
		AddInstanceOperationGlobalAction addInsCompAction = new AddInstanceOperationGlobalAction();
		assertEquals(0, panel.getPaintComponents().size());
		addInsCompAction.setConstructorToSet(String.class.getConstructors()[0]);
		addInsCompAction.execute(panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof InstanceOperationComponent);
		
		//add a method based on the operation component
		AddInstanceMethodGlobalAction addInsMthAction = new AddInstanceMethodGlobalAction();
		addInsMthAction.setInsComp(addInsCompAction.getInsComp());
		addInsMthAction.setMethodToSet(String.class.getMethods()[0]);
		addInsMthAction.execute(panel);
		assertEquals(2, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(1) instanceof MethodPaintComponent);
	}

}