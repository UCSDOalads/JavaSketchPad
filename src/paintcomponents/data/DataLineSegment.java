package paintcomponents.data;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import file.PanelIO;
import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class DataLineSegment extends LineSegment {

	public DataLineSegment(DataFromPoint fromPoint, DataToPoint toPoint) {
		super(fromPoint, toPoint);
		fromPoint.setLineSegment(this);
		toPoint.setLineSegment(this);

	}
	
	@Override
	public DataFromPoint getFromPoint() {
		return (DataFromPoint) super.getFromPoint();
	}
	

	@Override
	public DataToPoint getToPoint() {
		return (DataToPoint) super.getToPoint();
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//despite the fact that line segment does  override save to Element method, this method still saves it under a different name
		//TODO reconsider this design
		
		Element main = doc.createElement("datalinesegment");
		Element fromPoint = doc.createElement("frompoint");
		Element toPoint = doc.createElement("topoint");
		
		main.appendChild(fromPoint);
		main.appendChild(toPoint);
		rootElement.appendChild(main);
		
		
		fromPoint.setAttribute("id", Long.toString(getFromPoint().getComponentID()));
		toPoint.setAttribute("id", Long.toString(getToPoint().getComponentID()));

	}
	
	/**
	 * This method is not delegated to super(rootElement) but to super(fromPoint, toPoint)
	 * @param rootElement
	 */
	public DataLineSegment(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		//now eveything except the fromPoint and toPoint are set
		//find the fromPoint and to Point from all the types of PaintIO
		//first get the ids of two component
		
		Element main = (Element) rootElement.getElementsByTagName("datalinesegment").item(0);
		Element fromPointElem = (Element) main.getElementsByTagName("frompoint").item(0);
		Element toPointElem = (Element) main.getElementsByTagName("topoint").item(0);
		
		long fromPointID = PanelIO.idMapping.get(Long.parseLong(fromPointElem.getAttribute("id")));
		long toPointID = PanelIO.idMapping.get(Long.parseLong(toPointElem.getAttribute("id")));

		
		//search all dataios to fint the from and to points
		
		ArrayList<PaintComponent> paintComponents = panel.getPaintComponents();

		for (PaintComponent paintComponent : paintComponents) {
			if(paintComponent instanceof DataTextIOPaintComponent){
				DataTextIOPaintComponent io = (DataTextIOPaintComponent) paintComponent;
				ArrayList<DataFromPoint> fromPoints = io.getFromPoints();
				for (DataFromPoint dataFromPoint : fromPoints) {
					if(dataFromPoint.getComponentID() == fromPointID){
						setFromPoint(dataFromPoint);
						dataFromPoint.setLineSegment(this);
					}

				}
				ArrayList<DataToPoint> toPoints = io.getToPoints();
				for (DataToPoint dataToPoint : toPoints) {
					if(dataToPoint.getComponentID() == toPointID){
						setToPoint(dataToPoint);
						dataToPoint.setLineSegment(this);
					}
				}
				

			}
		}
	}

}
