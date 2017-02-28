package paintcomponents;

public class DataLineSegment<T> extends LineSegment {

	public DataLineSegment(DataFromPoint<T> fromPoint, DataToPoint<T> toPoint) {
		super(fromPoint, toPoint);
		fromPoint.setLineSegment(this);
		toPoint.setLineSegment(this);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataFromPoint<T> getFromPoint() {
		return (DataFromPoint<T>) super.getFromPoint();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public DataToPoint<T> getToPoint() {
		return (DataToPoint<T>) super.getToPoint();
	}

}
