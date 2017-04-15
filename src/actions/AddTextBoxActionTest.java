package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.TextPaintComponent;
import paintcomponents.data.DataDisplayPaintComponent;
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

		assertEquals(0, panel.getPaintComponents().size());

		action.performAction();

		assertEquals(1, panel.getPaintComponents().size());

		assertTrue(panel.getPaintComponents().get(0) instanceof TextPaintComponent);
	}

}
