package actions.global.globalactions;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import paintcomponents.LineSegment;
import paintcomponents.SimplePoint;
import ui.PaintPanel;

public class ConstructLineSegmentGlobalAction extends GlobalPaintAction {

	private SimplePoint fromPoint, toPoint;
	private LineSegment lineSegment;
	
	public void setFromPoint(SimplePoint inputFromPt) {
		fromPoint = inputFromPt;
	}
	
	public void setToPoint(SimplePoint inputToPt){
		toPoint = inputToPt;
	}
	
	public LineSegment getLineSeg(){
		
		return lineSegment;
		
	}
	
	@Override
	protected void execute(PaintPanel panel) {

		//construct line segment
		lineSegment = new LineSegment(fromPoint,toPoint );


		//add to panel
		panel.addPaintComponent(lineSegment);

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

			@Override
			protected String commandName() {
				return "construct lineSegment";
			}

			@Override
			protected String commandDescription() {
				return "[Deprecated] construct a generic line segment";
			}
		}); 
	}
	

}
