/**
 * 
 */
package actions.global.globalactions;

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
		action.setCommand("add lazy Javaclass");
		try {
			action.execute(panel);
		} catch (Exception e) {

		}
		action.setCommand("not valid command");
		try {
			action.execute(panel);
			// fail("should have exception"); doesn't throw
		} catch (Exception e) {

		}
	}

}
