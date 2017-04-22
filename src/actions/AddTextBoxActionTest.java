package actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

/**
 * test the addTextBoxAction class
 * 
 * @author Bingjie Zhou
 *
 */
public class AddTextBoxActionTest {

	/**
	 * test the type of box and size of panel component
	 */
	@Test
	public void test() {

		PaintPanel panel = new PaintPanel();

		AddTextBoxAction action = new AddTextBoxAction(panel);

		// panel should be of size 0
		assertEquals(0, panel.getPaintComponents().size());

		action.performAction();

		// after perform the add text box action, the panel should now
		// hold one component
		assertEquals(1, panel.getPaintComponents().size());

		// check if the component is a TextPaintComponent
		assertTrue(panel.getPaintComponents().get(0) instanceof TextPaintComponent);
	}

}
