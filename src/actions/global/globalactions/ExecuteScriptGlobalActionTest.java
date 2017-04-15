/**
 * 
 */
package actions.global.globalactions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ui.PaintPanel;

/**
 * @author Tianyiz
 *
 */
public class ExecuteScriptGlobalActionTest {

	/**
	 * Test method for
	 * {@link actions.global.globalactions.ExecuteScriptGlobalAction#execute(ui.PaintPanel)}
	 * .
	 */
	@Test
	public void testExecute() {
		ExecuteScriptGlobalAction action = new ExecuteScriptGlobalAction();
		PaintPanel panel = new PaintPanel();
		assertEquals(0, panel.getPaintComponents().size());
		action.setCommand("add data displayBox");
		action.execute(panel);
		assertEquals(1, panel.getPaintComponents().size());
		// action.setCommand("construct dataLineSegment"); this line create null
		// pointer exception bug
		action.execute(panel);
		assertEquals(2, panel.getPaintComponents().size());
	}

}
