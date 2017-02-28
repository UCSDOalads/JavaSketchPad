package paintcomponents;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

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

	public DataFromPoint(int x, int y) {
		super(x, y);
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


}
