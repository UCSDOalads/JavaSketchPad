package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import actions.global.ActionName;
import paintcomponents.haskell.HaskellExpressionPaintComponent;
import ui.PaintPanel;

public class AddHaskellComponentGlobalActionTest {

	PaintPanel panel;
	AddHaskellComponentGlobalAction action;
	
	@Before
	public void setUp() throws Exception {
		panel = new PaintPanel();

		action = (AddHaskellComponentGlobalAction) ActionName.ADD_HASKELL_EXPRESSION_COMPONENT
				.getAssiciatedAction();
		action.setHaskellExpression("2");
	}

	@Test
	public void testExecute() {

		assertEquals(0, panel.getPaintComponents().size());

		action.execute(panel);

		assertEquals(1, panel.getPaintComponents().size());

		assertTrue(panel.getPaintComponents().get(0) instanceof HaskellExpressionPaintComponent);
	}


	// TODO
	// currently it is giving out error:
	// Cannot run program "ghci": error=2, No such file or directory

}
