package actions;

import java.awt.Component;
import java.util.ArrayList;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
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
		//get selected components
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		//construct line segment
		LineSegment lineSegment = new LineSegment((SimplePoint)(items.get(0)), (SimplePoint)(items.get(1)));
		

		addLineSegment(lineSegment);
	}
	/**
	 * This method updates the panel's list of paint components and selection after a line segment is added
	 * Subclasses should call this method to update the panel when customizing the addition of a line segment 
	 * 
	 * @param lineSegment the lineSegment to be added to the painting panel
	 */
	protected void  addLineSegment(LineSegment lineSegment) {
		
		//add to panel
		panel.addPaintComponent(lineSegment);
		
		//change selection
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(lineSegment);
		//push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				lineSegment.remove(panel);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				panel.addPaintComponent(lineSegment);
				panel.repaint();
			}
		});
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Construct/Line Segment").toString();
	}

}
