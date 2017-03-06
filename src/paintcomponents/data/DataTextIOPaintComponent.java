package paintcomponents.data;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import file.PanelIO;
import painttools.tools.SelectTool;
import ui.PaintPanel;

/**
 * A data text with surrounding points on either side
 * 
 * @author chenzb
 *
 */
public class DataTextIOPaintComponent extends DataTextPaintComponent {

	private static final int HORIZONTAL_OFFSET = 10;

	private class DataFromPointInfo {
		public DataFromPointInfo(DataFromPoint fromPoint, int yShift) {
			super();
			this.fromPoint = fromPoint;
			this.yShift = yShift;
		}

		DataFromPoint fromPoint;
		int yShift;
	}

	private class DataToPointInfo {
		public DataToPointInfo(DataToPoint toPoint, int yShift) {
			super();
			this.toPoint = toPoint;
			this.yShift = yShift;
		}

		DataToPoint toPoint;
		int yShift;
	}

	/**
	 * From Points are displayed on the right, points leaving this paint
	 * component
	 * 
	 * @return the fromPoints
	 */
	protected ArrayList<DataFromPoint> getFromPoints() {
		return fromPoints.stream().map(e -> e.fromPoint)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * To Points are displayed on the left, points arriving at this component
	 * 
	 * @return the toPoints
	 */
	protected ArrayList<DataToPoint> getToPoints() {
		return toPoints.stream().map(e -> e.toPoint)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private ArrayList<DataFromPointInfo> fromPoints;
	private ArrayList<DataToPointInfo> toPoints;

	public DataTextIOPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		fromPoints = new ArrayList<>();
		toPoints = new ArrayList<>();
	}

	/**
	 * Add a data from point, Data From Point by default places the dataPoint to
	 * the right by the amoount of HORIZONTAL_OFFSET
	 * 
	 * @param provider
	 *            the provider to query when a data is requested
	 * @param yShift
	 *            the row number of placing the data from point
	 */
	public void addFromPoint(DataFromPointDataProvider provider, int yShift, String expectedType) {
		DataFromPoint fromPoint = new DataFromPoint(getX(), getY(), expectedType);
		fromPoint.setProvider(provider);
		fromPoints.add(new DataFromPointInfo(fromPoint, yShift));
	}

	/**
	 * Adds a data to point The DataToPoint always shows on the left by the
	 * amount indicated on the HORIZONTAL_OFFSET
	 * 
	 * @param yShift
	 *            the row number
	 * @return the added toPoint
	 */
	public void addToPoint(int yShift, String expectedType) {
		DataToPoint toPoint = new DataToPoint(getX(), getY(), expectedType);
		toPoints.add(new DataToPointInfo(toPoint, yShift));

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
			fromPoint.setX((int) (getX() + this.getBounds().getWidth()
					+ HORIZONTAL_OFFSET));
			fromPoint.setY((int) (getY() + this.getRowHeight() / 2
					+ this.getRowHeight() * dataFromPointInfo.yShift));
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			toPoint.setX((int) (getX() - HORIZONTAL_OFFSET));
			toPoint.setY((int) (getY() + this.getRowHeight() / 2
					+ this.getRowHeight() * dataToPointInfo.yShift));
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
			if (fromPoint.contains(x, y)) {
				return true;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if (toPoint.contains(x, y)) {
				return true;
			}
		}
		return super.contains(x, y);
	}

	@Override
	public void select(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		// try to select every from and toPoints
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if (fromPoint.contains(x, y)) {
				fromPoint.select(selectTool);
				return;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if (toPoint.contains(x, y)) {
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

		// try to deselect every from and toPoints
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if (fromPoint.contains(x, y)) {
				fromPoint.deselect(selectTool);
				return;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if (toPoint.contains(x, y)) {
				toPoint.deselect(selectTool);
				return;
			}
		}
		super.deselect(selectTool);
	}

	@Override
	public boolean isSelected() {
		// if any of the point is selected, this component is considered
		// selected, and cannot be selected again
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			DataFromPoint fromPoint = dataFromPointInfo.fromPoint;
			if (fromPoint.isSelected()) {
				return true;
			}
		}
		for (DataToPointInfo dataToPointInfo : toPoints) {
			DataToPoint toPoint = dataToPointInfo.toPoint;
			if (toPoint.isSelected()) {
				return true;
			}
		}
		return super.isSelected();
	}
	
	@Override
	// IMPORTANT , points not in panel.paintComponents, so won't removed by regular remove method
	public void remove(PaintPanel panel) {
		// TODO Auto-generated method stub
		for(DataFromPoint fromPoint: getFromPoints()) {
			if(fromPoint.getLineSegment() != null)
				fromPoint.getLineSegment().remove(panel);
		}
		for(DataToPoint toPoint: getToPoints()) {
			if(toPoint.getLineSegment() != null)
				toPoint.getLineSegment().remove(panel);
		}
		super.remove(panel);
		
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//create elements
		Element main = doc.createElement("datatextiopaintcomponent");
		for (DataFromPointInfo dataFromPointInfo : fromPoints) {
			Element fromPointsElem = doc.createElement("frompoint");
			fromPointsElem.setAttribute("id", Long.toString(dataFromPointInfo.fromPoint.getComponentID()));
			fromPointsElem.setAttribute("yshift", Integer.toString(dataFromPointInfo.yShift));
			dataFromPointInfo.fromPoint.saveToElement(fromPointsElem, doc);
			main.appendChild(fromPointsElem);
		}
		
		for (DataToPointInfo dataToPointInfo : toPoints) {
			Element toPointElem = doc.createElement("topoint");
			toPointElem.setAttribute("id", Long.toString(dataToPointInfo.toPoint.getComponentID()));
			toPointElem.setAttribute("yshift", Integer.toString(dataToPointInfo.yShift));
			dataToPointInfo.toPoint.saveToElement(toPointElem, doc);
			main.appendChild(toPointElem);
		}
		
		rootElement.appendChild(main);
		
	}

	/**
	 * The default implementations does not reconstruct points, subclasses
	 * must call <code>linkPoints</code> after manually reconstruct all points, to ensure
	 * respective line segments can be reconstructed
	 * @param rootElement
	 */
	public DataTextIOPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		fromPoints = new ArrayList<>();
		toPoints = new ArrayList<>();
		
		
	}
	
	/**
	 * Link all constructed points with their original reference, update PanelIO.linkedPoints list
	 * 
	 * @param rootElement
	 */
	protected void linkPoints(Element rootElement){
		Element main = (Element) rootElement.getElementsByTagName("datatextiopaintcomponent").item(0);
		NodeList fromPointElems = main.getElementsByTagName("frompoint");
		for(int i = 0; i < fromPointElems.getLength(); i ++){
			Element fromPointElem = (Element) fromPointElems.item(i);

			DataFromPoint fromPoint  = fromPoints.get(i).fromPoint;
//			= new DataFromPoint(fromPointElem);
//			int yShift = Integer.parseInt(fromPointElem.getAttribute("yshift"));
//			DataFromPointInfo info = new DataFromPointInfo(fromPoint, yShift);
//			fromPoints.add(info);
//			
//			//important :: update global hash
			PanelIO.idMapping.put(Long.parseLong(fromPointElem.getAttribute("id")), fromPoint.getComponentID());
		}
		NodeList toPointElems = main.getElementsByTagName("topoint");
		for(int i = 0; i < toPointElems.getLength(); i ++){
			Element toPointElem = (Element) toPointElems.item(i);
			DataToPoint toPoint = toPoints.get(i).toPoint;
//			= new DataToPoint(toPointElem);
//			int yShift = Integer.parseInt(toPointElem.getAttribute("yshift"));
//			DataToPointInfo info = new DataToPointInfo(toPoint, yShift);
//			toPoints.add(info);
			
			//important :: update global hash
			PanelIO.idMapping.put(Long.parseLong(toPointElem.getAttribute("id")), toPoint.getComponentID());
		}
	}

}
