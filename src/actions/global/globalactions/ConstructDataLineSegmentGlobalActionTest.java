package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataLineSegment;
import paintcomponents.data.DataToPoint;
import typesystem.JavaType;
import ui.PaintPanel;

/**
 * Test for construct data line segment global action
 * 
 * @author Shanfeng Feng
 *
 */
public class ConstructDataLineSegmentGlobalActionTest {

	@Test
	public void test() {

		// check if data line segment is successfully added
		PaintPanel panel = new PaintPanel();
		ConstructDataLineSegmentGlobalAction action = new ConstructDataLineSegmentGlobalAction();

		// data input point store input "Testing Global"
		DataFromPoint testFromPt = new DataFromPoint(50, 50, new JavaType(String.class));
		testFromPt.setProvider(new DataFromPointDataProvider() {

			@Override
			public Object provideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return "Testing Global";
			}

			@Override
			public boolean canProvideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return true;
			}

		});
		DataToPoint testToPt = new DataToPoint(50, 125, new JavaType(String.class));

		// add the points to the panel
		panel.addPaintComponent(testFromPt);
		panel.addPaintComponent(testToPt);

		action.setFromPoint(testFromPt);
		action.setToPoint(testToPt);

		// build a data line segment between them
		action.execute(panel);

		assertEquals(3, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(2) instanceof DataLineSegment);

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

		// input data changes, output data changes as well
		testFromPt.setProvider(new DataFromPointDataProvider() {

			@Override
			public Object provideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return "Data changed";
			}

			@Override
			public boolean canProvideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
				return true;
			}

		});

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
