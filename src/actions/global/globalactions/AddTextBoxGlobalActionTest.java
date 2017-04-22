package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

/**
 * test the addTextBoxGlobalAction class
 * 
 * @author Bingjie Zhou
 *
 */
public class AddTextBoxGlobalActionTest {

	/**
	 * test the type and size of component and panel
	 */
	@Test
	public void test() {

		PaintPanel panel = new PaintPanel();

		AddTextBoxGlobalAction action = new AddTextBoxGlobalAction();

		// panel should now hold nothing and hence be size of 0
		assertEquals(0, panel.getPaintComponents().size());

		action.execute(panel);

		// the size should now be 1
		assertEquals(1, panel.getPaintComponents().size());

		// assert the first component of the panel is a TextPaintComponent
		assertTrue(panel.getPaintComponents().get(0) instanceof TextPaintComponent);
	}

}
