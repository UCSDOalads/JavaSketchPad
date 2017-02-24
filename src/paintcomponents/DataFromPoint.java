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
public class DataFromPoint<T> extends SimplePoint {
	
	
	DataLineSegment<T> lineSegment;
	Queue<T> datas;

	/**
	 * @return the lineSegment
	 */
	public DataLineSegment<T> getLineSegment() {
		return lineSegment;
	}

	/**
	 * @param lineSegment
	 *            the lineSegment to set
	 */
	public void setLineSegment(DataLineSegment<T> lineSegment) {
		this.lineSegment = lineSegment;
	}

	public DataFromPoint(int x, int y) {
		super(x, y);
		datas = new LinkedBlockingQueue<>();
	}
	
	public void offer(T data){
		this.datas.offer(data);
	}
	
	/**
	 * Fetches the data, users should not try to call this method, except from DataToPoint class
	 * returns null if the underlying queue is empty.
	 * @param data
	 */
	protected T poll(){
		return this.datas.poll();
	}

}
