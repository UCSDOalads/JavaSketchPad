package actions;

import java.util.ArrayList;

import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import ui.PaintPanel;

public class ConstructLineSegmentAction extends PaintAction {

	public ConstructLineSegmentAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		//get selected components
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		//only two points can be selected
		if(items.size() != 2) return false;
		//selected component must be of type point
		for (PaintComponent paintComponent : items) {
			if(!(paintComponent instanceof SimplePoint)){
				return false;
			}
		}
		//TODO If line segment already exists, do not add again!!!
		//TODO Do not allow adding two line segments connecting the same point
		return true;
		
	}

	@Override
	public void performAction() {
		//get selected components
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		//construct line segment
		LineSegment lineSegment = new LineSegment((SimplePoint)(items.get(0)), (SimplePoint)(items.get(1)));
		//add to panel
		panel.addPaintComponent(lineSegment);
		
		//change selection
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(lineSegment);
		panel.repaint();
		

	}

	@Override
	public String locationString() {
		return "Construct/Line Segment";
	}

}
