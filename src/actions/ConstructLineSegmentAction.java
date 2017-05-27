package actions;

import java.util.ArrayList;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import ui.PaintPanel;

public class ConstructLineSegmentAction extends MenuBarPaintAction {

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
		
		//check if line segment already exist
		// if exists, then it will not form a new line segment
		ArrayList<PaintComponent> components = panel.getPaintComponents();
		LineSegment line = null;
		
		//get all paintComponents
		for(PaintComponent paintComponent : components) {
			if( paintComponent instanceof LineSegment ) {
				line = (LineSegment) paintComponent;
				//check front point and to point similarities
				if(items.get(0) == line.getFromPoint() && 
					items.get(1) == line.getToPoint())
					return false;
				if(items.get(1) == line.getFromPoint() && 
					items.get(0) == line.getToPoint())
					return false;
			}
		}
		
		//TODO Do not allow adding two line segments connecting the same point
		return true;
		
	}

	@Override
	public void performAction() {

		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		ConstructLineSegmentGlobalAction associatedAction = 
		(ConstructLineSegmentGlobalAction) 
		ActionName.CONSTRUCT_LINE_SEGMENT_ACTION.getAssociatedAction();
		
		associatedAction.setFromPoint((SimplePoint)(items.get(0)));
		associatedAction.setToPoint((SimplePoint)(items.get(1)));
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
		
		//change selection
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(associatedAction.getLineSeg());
		
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Construct/Line Segment").toString();
	}
}
