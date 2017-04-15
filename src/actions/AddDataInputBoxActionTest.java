package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class AddDataInputBoxActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		AddDataInputBoxAction action = new AddDataInputBoxAction(panel);

		assertEquals(0, panel.getPaintComponents().size());

		action.performAction();

		assertEquals(1, panel.getPaintComponents().size());

		assertTrue(panel.getPaintComponents().get(0) instanceof DataInputTextfieldPaintComponent);

	}

}
