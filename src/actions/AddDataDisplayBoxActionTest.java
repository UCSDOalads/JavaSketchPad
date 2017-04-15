package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

/**
 * AddDataDisplayBoxAction tester
 * @author Joe Helen
 * test getPaintcomponents size. paintComponents goes from 0 to 1 when it performs the action.
 */
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
