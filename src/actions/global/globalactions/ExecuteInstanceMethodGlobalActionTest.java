package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import actions.global.ActionName;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class ExecuteInstanceMethodGlobalActionTest extends ExecuteInstanceMethodGlobalAction {
	
	
	PaintPanel panel;
	ExecuteInstanceMethodGlobalAction  action;
	public void setUp() throws Exception{
		panel = new PaintPanel();
		action = (ExecuteInstanceMethodGlobalAction)ActionName.EXECUTE_INSTANCE_CONSTRUCTOR_ACTION
				.getAssiciatedAction();
				
	}

	@Test
	public void test() {
					
		assertEquals(0, panel.getPaintComponents().size());
				
		// Testing performAction
		action.execute(panel);
		
		assertEquals(1, panel.getPaintComponents().size());
		
		assertTrue(panel.getPaintComponents().get(0) instanceof DataInputTextfieldPaintComponent);	

		}
	}


