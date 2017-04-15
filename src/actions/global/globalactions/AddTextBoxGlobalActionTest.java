package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.TextPaintComponent;
import paintcomponents.data.DataDisplayPaintComponent;
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

		assertEquals(0, panel.getPaintComponents().size());

		action.execute(panel);

		assertEquals(1, panel.getPaintComponents().size());

		assertTrue(panel.getPaintComponents().get(0) instanceof TextPaintComponent);
	}

}
