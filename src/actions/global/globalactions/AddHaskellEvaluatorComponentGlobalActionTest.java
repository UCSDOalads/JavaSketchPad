package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import actions.global.ActionName;
import paintcomponents.haskell.EvaluateHaskellPaintComponent;
import ui.PaintPanel;

public class AddHaskellEvaluatorComponentGlobalActionTest {

	PaintPanel panel;
	AddHaskellEvaluatorComponentGlobalAction action;

	@Before
	public void setUp() throws Exception {
		panel = new PaintPanel();

		action = (AddHaskellEvaluatorComponentGlobalAction) ActionName.ADD_HASKELL_EVALUATOR_COMPONENT
				.getAssiciatedAction();
	}

	@Test
	public void testExecute() {
		assertEquals(0, panel.getPaintComponents().size());

		action.execute(panel);

		assertEquals(1, panel.getPaintComponents().size());

		assertTrue(panel.getPaintComponents().get(0) instanceof EvaluateHaskellPaintComponent);
	}


}
