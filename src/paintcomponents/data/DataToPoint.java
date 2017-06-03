package paintcomponents.data;

import java.util.NoSuchElementException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.SimplePoint;
import typesystem.Type;
import ui.PaintPanel;

/**
 * Data to point is the point where data is flowing to.
 * You can "get" data from this point
 * @author chenzb
 *
 */
public class DataToPoint extends SimplePoint {
	
	
	DataLineSegment lineSegment;
	private Type expectedType;

	/**
	 * @return the lineSegment
	 */
	public DataLineSegment getLineSegment() {
		return lineSegment;
	}

	/**
	 * @param lineSegment the lineSegment to set
	 */
	public void setLineSegment(DataLineSegment lineSegment) {
		this.lineSegment = lineSegment;
	}

	public DataToPoint(int x, int y, Type expectedType) {
		super(x, y);
		this.expectedType = expectedType;
	}
	
	
	/**
	 * Try to fetch a data from a connecting point
	 * 
	 * @throws NoConnectingLineSegmentException when this point is not connected to a valid line segment
	 * @throws NoSuchElementException when the connecting in point does not have a data to offer
	 * @throws DataFromPointProviderCannotProvideDataException  when the connecting dataInputPoint's data provider cannot provide a information
	 * @throws DataFromPointNoDataProviderException  when the connecting DataInputPoint does not have a data provider associated
	 */
	public Object fetchData() throws NoConnectingLineSegmentException, NoSuchElementException,
				DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException{
		if(this.lineSegment == null) throw new NoConnectingLineSegmentException();
		
		Object returnVal = lineSegment.getFromPoint().getData();// .toString();
		if(returnVal == null) throw new NoSuchElementException();
		return returnVal;
		
	}

	public Type getExpectedType() {
		return expectedType;
	}

	public void setExpectedType(Type expectedType) {
		this.expectedType = expectedType;
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		// TODO Auto-generated method stub
		super.saveToElement(rootElement, doc);
	}

	public DataToPoint(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		// TODO Auto-generated constructor stub
	}
	
	

}
