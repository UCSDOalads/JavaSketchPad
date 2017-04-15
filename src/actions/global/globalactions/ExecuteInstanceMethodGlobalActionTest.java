package actions.global.globalactions;

import static org.junit.Assert.*;



import org.junit.Test;

import ui.PaintPanel;

public class ExecuteInstanceMethodGlobalActionTest {


	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		
		AddLazyJavaClassGlobalAction addClassAction = new AddLazyJavaClassGlobalAction();
		
		
		addClassAction.execute(panel);;

		assertEquals(1,panel.getPaintComponents().size());
		
	
		
	}
}
