package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;
import actions.AddDataDisplayBoxAction;

public class AddDataDisplayBoxGlobalActionTest {

	@Test
	public void test() {
PaintPanel panel = new PaintPanel();
		
		AddDataDisplayBoxGlobalAction action = new AddDataDisplayBoxGlobalAction();
		
		assertEquals(0, panel.getPaintComponents().size());
		
		action.execute(panel);
		
		assertEquals(1, panel.getPaintComponents().size());
		
		assertTrue(panel.getPaintComponents().get(0) instanceof DataDisplayPaintComponent);
	}

}
