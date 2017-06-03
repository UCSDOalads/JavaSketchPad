package paintcomponents.data;

import java.util.NoSuchElementException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import typesystem.JavaType;
import ui.PaintPanel;

/**
 * The data display paint component displays the data with a asoociated DataToPoint
 * @author chenzb
 *
 */
//TODO THIS class is a copy of DataInputTextfieldPaintComponent class, please consider abstraction
public class DataDisplayPaintComponent extends DataTextIOPaintComponent {


	public DataDisplayPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		addToPoint(0, new JavaType(String.class));
	}

	/**
	 * Update the current display.
	 * 
	 * This class will try to fetch the data from the toPointClass
	 * @throws DataFromPointProviderCannotProvideDataException 
	 * @throws DataFromPointNoDataProviderException 
	 * @throws NoConnectingLineSegmentException 
	 * @throws NoSuchElementException 
	 * @see DataToPoint.fetchData for exception details
	 */
	public void updateDisplayText() throws NoSuchElementException, NoConnectingLineSegmentException, DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException{
		Object data = getToPoints().get(0).fetchData();/*
														 * .toString();// add //
														 * toString // to ignore
														 * // error
														 */
		this.setDisplayingText(data.toString());
		/*if(data instanceof String){
			this.setDisplayingText(data.toString());
		} else {
			this.setDisplayingText("Error: Not A String :: " + "Type : " + data.getClass().toString() + " Value: " + data.toString());
		}*/
	}
	
	public boolean canUpdate() {
		return true; // temporary
		/*
		 * try { Object data = getToPoints().get(0).fetchData(); if (data
		 * instanceof String) { return true; } } catch (Exception e) { return
		 * false; } return false;
		 */
	}

	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//since the original class already saved the displaying text, there is no need for us to do the same thing
	}

	public DataDisplayPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		//we only need to do the same thing as our designated constructor
		addToPoint(0, new JavaType(String.class));
		//we have to link the points
		linkPoints(rootElement);

	}

}
