package paintcomponents;

import java.awt.Graphics;
import java.util.ArrayList;

import painttools.tools.SelectTool;

/**
 * A data text with surrounding points on either side
 * 
 * @author chenzb
 *
 */
public class DataTextIOPaintComponent extends DataTextPaintComponent
{

	private static final int HORIZONTAL_OFFSET = 10;
	
	private class DataFromPointInfo{
		public DataFromPointInfo(DataFromPoint fromPoint, int yShift) {
			super();
			this.fromPoint = fromPoint;
			this.yShift = yShift;
		}
		DataFromPoint fromPoint;
		int yShift;
	}
	
	private class DataToPointInfo{
		public DataToPointInfo(DataToPoint toPoint, int yShift) {
			super();
			this.toPoint = toPoint;
			this.yShift = yShift;
		}
		DataToPoint toPoint;
		int yShift;
	}
	
	
	private ArrayList<DataFromPointInfo> fromPoints;
	private ArrayList<DataToPointInfo> toPoints;

	public DataTextIOPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		fromPoints = new ArrayList<>();
		toPoints = new ArrayList<>();
	}
	
	/**
	 * Add a data from point, 
	 * Data From Point by default places the dataPoint to the right by the amoount of HORIZONTAL_OFFSET
	 * 
	 * @param provider the provider to query when a data is requested
	 * @param yShift the row number of placing the data from point
	 */
	public void addFromPoint(DataFromPointDataProvider provider, int yShift){
		DataFromPoint fromPoint = new DataFromPoint(getX(), getY());
		fromPoint.setProvider(provider);
		fromPoints.add(new DataFromPointInfo(fromPoint, yShift));
	}
	
	/**
	 * Adds a data to point
	 * The DataToPoint always shows on the left by the amount indicated on the HORIZONTAL_OFFSET
	 * @param yShift the row number
	 * @return the added toPoint
	 */
	public DataToPoint addToPoint(int yShift){
		DataToPoint toPoint = new DataToPoint(getX(), getY());
		toPoints.add(new DataToPointInfo(toPoint, yShift));
		return toPoint;
		
	}

	@Override
	protected void paintSelected(Graphics g) {
		super.paintSelected(g);
		updatePointsPosition();
		fromPoints.forEach(e -> e.fromPoint.paint(g));
		toPoints.forEach(e -> e.toPoint.paint(g));
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		super.paintNotSelected(g);
		updatePointsPosition();
		fromPoints.forEach(e -> e.fromPoint.paint(g));
		toPoints.forEach(e -> e.toPoint.paint(g));
	}

	/**
	 * This method will use the protected bounds, which will be updated in
	 * super.paint[Not]Selected. Make sure you've already invoked super's
	 * paintNotSelectedMethod before invoking this one.
	 */
	private void updatePointsPosition() {
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			fromPoint.setX(
					(int) (getX() + this.bounds.getWidth() + HORIZONTAL_OFFSET));
			fromPoint.setY((int) (getY() + this.bounds.getHeight() / 2 + this.bounds.getHeight() * dataFromPointInfo.yShift));
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			toPoint.setX(
				(int) (getX() - HORIZONTAL_OFFSET));
			toPoint.setY((int) (getY() + this.bounds.getHeight() / 2 + this.bounds.getHeight() * dataToPointInfo.yShift));
		}
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		fromPoints.forEach(e -> e.fromPoint.translate(i, j));
		toPoints.forEach(e -> e.toPoint.translate(i, j));
	}

	@Override
	public boolean contains(int x, int y) {

		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if(fromPoint.contains(x, y)){
				return true;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if(toPoint.contains(x, y)){
				return true;
			}
		}
		return super.contains(x, y);
	}




	@Override
	public void select(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		//try to select every from and toPoints
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if(fromPoint.contains(x, y)){
				fromPoint.select(selectTool);
				return;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if(toPoint.contains(x, y)){
				toPoint.select(selectTool);
				return;
			}
		}
		super.select(selectTool);
	}

	@Override
	public void deselect(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		
		
		//try to deselect every from and toPoints
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if(fromPoint.contains(x, y)){
				fromPoint.deselect(selectTool);
				return;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if(toPoint.contains(x, y)){
				toPoint.deselect(selectTool);
				return;
			}
		}
		super.deselect(selectTool);
	}
	
	@Override
	public boolean isSelected() {
		//if any of the point is selected, this component is considered selected, and cannot be selected again
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if(fromPoint.isSelected()){
				return true;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if(toPoint.isSelected()){
				return true;
			}
		}
		return super.isSelected();
	}

	

	
}
