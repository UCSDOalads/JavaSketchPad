package paintcomponents;

import java.util.NoSuchElementException;

/**
 * Data to point is the point where data is flowing to.
 * You can "get" data from this point
 * @author chenzb
 *
 */
public class DataToPoint<T> extends SimplePoint {
	
	
	DataLineSegment<T> lineSegment;

	/**
	 * @return the lineSegment
	 */
	public DataLineSegment<T> getLineSegment() {
		return lineSegment;
	}

	/**
	 * @param lineSegment the lineSegment to set
	 */
	public void setLineSegment(DataLineSegment<T> lineSegment) {
		this.lineSegment = lineSegment;
	}

	public DataToPoint(int x, int y) {
		super(x, y);
	}
	
	
	/**
	 * Try to fetch a data from a connecting point
	 * 
	 * @throws NoConnectingLineSegmentException when this point is not connected to a valid line segment
	 * @throws NoSuchElementException when the connecting in point does not have a data to offer
	 * @throws DataFromPointProviderCannotProvideDataException  when the connecting dataInputPoint's data provider cannot provide a information
	 * @throws DataFromPointNoDataProviderException  when the connecting DataInputPoint does not have a data provider associated
	 */
	public T fetchData() throws NoConnectingLineSegmentException, NoSuchElementException, DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException{
		if(this.lineSegment == null) throw new NoConnectingLineSegmentException();
		
		T returnVal = lineSegment.getFromPoint().getData();
		if(returnVal == null) throw new NoSuchElementException();
		return returnVal;
		
	}
	

}
