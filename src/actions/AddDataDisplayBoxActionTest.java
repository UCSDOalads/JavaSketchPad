package actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;

/**
 * AddDataDisplayBoxAction tester
 * 
 * @author Joe Helen test getPaintcomponents size. paintComponents goes from 0
 *         to 1 when it performs the action.
 */
public class AddDataDisplayBoxActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);

		// panel has 0 component
		assertEquals(0, panel.getPaintComponents().size());

		action.performAction();

		// after perform action, panel now has 1 component
		assertEquals(1, panel.getPaintComponents().size());

		// check if the component is a DataDisplayPaintComponent
		assertTrue(panel.getPaintComponents().get(0) instanceof DataDisplayPaintComponent);
	}

}
