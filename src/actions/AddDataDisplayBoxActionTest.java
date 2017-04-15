package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class AddDataDisplayBoxActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		
		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);
		
		assertEquals(0, panel.getPaintComponents().size());
		
		action.performAction();
		
		assertEquals(1, panel.getPaintComponents().size());
		
		assertTrue(panel.getPaintComponents().get(0) instanceof DataDisplayPaintComponent);
	}

}
