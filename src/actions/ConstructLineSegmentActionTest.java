/**
 * 
 */
package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import paintcomponents.SimplePoint;
import painttools.tools.SelectTool;
import ui.PaintPanel;

/**
 * 
 * Test local line segment action.
 * @author Shanfeng Feng
 * 
 */
public class ConstructLineSegmentActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		SelectTool selectTool = new SelectTool(panel);
		panel.setSelectTool(selectTool);
		
		ConstructLineSegmentAction action = new ConstructLineSegmentAction(panel);
		
		SimplePoint testFromPt = new SimplePoint(50,50);
		SimplePoint testToPt = new SimplePoint(50, 125);
		
		panel.addPaintComponent(testFromPt);
		panel.addPaintComponent(testToPt);
		
		//should be false, no points selected
		assertTrue(!action.canPerformAction());
		testFromPt.select(selectTool);
		
		// should be false, only one point selected
		assertTrue(!action.canPerformAction());
		testToPt.select(selectTool);
		
		// should be true, two points selected
		assertTrue(action.canPerformAction());
		
		action.performAction();
		
		// should be false, since segment already exist
		assertTrue(!action.canPerformAction());
		
		// panel should now have 3 component
		assertEquals(3,panel.getPaintComponents().size());
		
	}

}
