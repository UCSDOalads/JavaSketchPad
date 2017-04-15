/**
 * 
 */
package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.LineSegment;
import paintcomponents.SimplePoint;
import ui.PaintPanel;

/**
 * @author Shanfeng Feng
 *
 */
public class ConstructLineSegmentGlobalActionTest {

	@Test
	public void test() {
	
		PaintPanel panel = new PaintPanel();
		ConstructLineSegmentGlobalAction action = new ConstructLineSegmentGlobalAction();
		
		SimplePoint testFromPt = new SimplePoint(50,50);
		SimplePoint testToPt = new SimplePoint(50, 125);
		panel.addPaintComponent(testFromPt);
		panel.addPaintComponent(testToPt);
		
		action.setFromPoint(testFromPt);
		action.setToPoint(testToPt);
		
		action.execute(panel);
		
		assertEquals(3,panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(2) instanceof LineSegment);
		
	}

}
