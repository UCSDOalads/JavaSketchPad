package paintcomponents.data;

import java.util.NoSuchElementException;

import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;

/**
 * The data display paint component displays the data with a asoociated DataToPoint
 * @author chenzb
 *
 */
//TODO THIS class is a copy of DataInputTextfieldPaintComponent class, please consider abstraction
public class DataDisplayPaintComponent extends DataTextIOPaintComponent {


	public DataDisplayPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		addToPoint(0, String.class.getName());
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
		Object data = getToPoints().get(0).fetchData();
		if(data instanceof String){
			this.setDisplayingText(data.toString());
		} else {
			this.setDisplayingText("Error: Not A String :: " + "Type : " + data.getClass().toString() + " Value: " + data.toString());
		}
	}

	public DataDisplayPaintComponent(Element rootElement) {
		super(rootElement);
		// TODO Auto-generated constructor stub
	}

}
