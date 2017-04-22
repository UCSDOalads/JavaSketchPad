package actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.SimplePoint;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataToPoint;
import painttools.tools.SelectTool;
import typesystem.JavaType;
import ui.PaintPanel;

/**
 * Test the construct data line segment
 * 
 * @author Xelafe
 *
 */
public class ConstructDataLineSegmentActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		SelectTool selectTool = new SelectTool(panel);
		panel.setSelectTool(selectTool);

		ConstructDataLineSegmentAction action = new ConstructDataLineSegmentAction(panel);

		// give input point a input value
		DataFromPoint testFromPt = new DataFromPoint(50, 50, new JavaType(String.class));
		testFromPt.setProvider(new DataFromPointDataProvider() {

			@Override
			public Object provideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return "Testing Local";
			}

			@Override
			public boolean canProvideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return true;
			}

		});
		DataToPoint testToPt = new DataToPoint(50, 125, new JavaType(String.class));
		SimplePoint thirdPt = new SimplePoint(50, 100);

		panel.addPaintComponent(testFromPt);
		panel.addPaintComponent(testToPt);

		// should be false, no points selected
		assertTrue(!action.canPerformAction());
		testFromPt.select(selectTool);

		// should be false, only one point selected
		assertTrue(!action.canPerformAction());
		testToPt.select(selectTool);

		// should be true, two points selected
		assertTrue(action.canPerformAction());

		// cannot make data line segment when selected more than 2 points
		thirdPt.select(selectTool);
		assertTrue(!action.canPerformAction());

		// cannot make data line segment when it is not between data point type
		testToPt.deselect(selectTool);
		assertTrue(!action.canPerformAction());

		// make the data line segment between the two data point
		thirdPt.deselect(selectTool);
		testToPt.select(selectTool);
		action.performAction();

		// should be false, since segment already exist
		assertTrue(!action.canPerformAction());

		// panel should now have 3 component
		assertEquals(3, panel.getPaintComponents().size());

		// since two data points are connected, their data must be the same.
		try {
			assertEquals(testFromPt.getProvider().provideInformationToDataFromPoint(testFromPt), testToPt.fetchData());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (NoConnectingLineSegmentException e) {
			e.printStackTrace();
		} catch (DataFromPointNoDataProviderException e) {
			e.printStackTrace();
		} catch (DataFromPointProviderCannotProvideDataException e) {
			e.printStackTrace();
		}

	}

}
