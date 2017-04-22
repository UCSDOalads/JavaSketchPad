package actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class AddDataInputBoxActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		AddDataInputBoxAction action = new AddDataInputBoxAction(panel);

		// panel has 0 component
		assertEquals(0, panel.getPaintComponents().size());

		action.performAction();

		// after perform action, panel now has 1 component
		assertEquals(1, panel.getPaintComponents().size());

		// check if the component is a DataDisplayPaintComponent
		assertTrue(panel.getPaintComponents().get(0) instanceof DataInputTextfieldPaintComponent);

	}

}
