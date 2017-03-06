package paintcomponents.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.SimplePoint;
import ui.PaintPanel;

/**
 * This point consumes data and tries to pass the data along a connecting line
 * segment
 * 
 * @author chenzb
 *
 */
public class DataFromPoint extends SimplePoint {

	private DataLineSegment lineSegment;
	private DataFromPointDataProvider provider;
	private String expectedType;

	/**
	 * @return the lineSegment
	 */
	public DataLineSegment getLineSegment() {
		return lineSegment;
	}

	/**
	 * @param lineSegment
	 *            the lineSegment to set
	 */
	public void setLineSegment(DataLineSegment lineSegment) {
		this.lineSegment = lineSegment;
	}

	public DataFromPoint(int x, int y, String expectedType) {
		super(x, y);
		this.expectedType = expectedType;
	}

	/**
	 * Fetches the data, users should not try to call this method, except from
	 * DataToPoint class
	 * 
	 * @param data
	 * @throws DataFromPointNoDataProviderException if provider for this method is not set
	 * @throws DataFromPointProviderCannotProvideDataException if the provider cannot provide such information
	 */
	protected Object getData() throws DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException {
		if (this.provider == null){
			throw new DataFromPointNoDataProviderException();
		}
		if (!this.provider.canProvideInformationToDataFromPoint(this)){
			throw new DataFromPointProviderCannotProvideDataException();
		}
		return this.provider.provideInformationToDataFromPoint(this);
	}

	public DataFromPointDataProvider getProvider() {
		return provider;
	}

	public void setProvider(DataFromPointDataProvider provider) {
		this.provider = provider;
	}

	public String getExpectedType() {
		return expectedType;
	}

	public void setExpectedType(String expectedType) {
		this.expectedType = expectedType;
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		// TODO Auto-generated method stub
		super.saveToElement(rootElement, doc);
	}

	public DataFromPoint(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		// TODO Auto-generated constructor stub
	}


}
