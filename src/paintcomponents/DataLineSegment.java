package paintcomponents;

public class DataLineSegment extends LineSegment {

	public DataLineSegment(DataFromPoint fromPoint, DataToPoint toPoint) {
		super(fromPoint, toPoint);
		fromPoint.setLineSegment(this);
		toPoint.setLineSegment(this);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataFromPoint getFromPoint() {
		return (DataFromPoint) super.getFromPoint();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public DataToPoint getToPoint() {
		return (DataToPoint) super.getToPoint();
	}

}
