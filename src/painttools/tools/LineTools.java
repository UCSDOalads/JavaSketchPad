//package painttools.tools;
//
//import java.awt.event.MouseEvent;
//
//import actions.global.ActionName;
//import actions.global.GlobalPaintAction;
//import actions.global.GlobalPaintActionExecuter;
//import actions.global.globalactions.ConstructLineSegmentGlobalAction;
//import paintcomponents.LineSegment;
//import paintcomponents.PaintComponent;
//import paintcomponents.SimplePoint;
//import ui.PaintPanel;
//
//public class LineTools extends PaintTool {
//
//	private PaintPanel panel;
//	private SimplePoint fromPoint, toPoint;
//	private State state;
//
//	public State getState() {
//		return state;
//	}
//
//	public void setState(State state) {
//		this.state = state;
//		switch (state) {
//		case DEFAULT:
//			break;
//		case SINGLE_POINT_SELECTED:
//			LineSegment lineSegment = new LineSegment(fromPoint, toPoint);
//
//		default:
//			break;
//		}
//	}
//
//	enum State {
//		DEFAULT, SINGLE_POINT_SELECTED
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		PaintComponent comp = panel.componentUnderPoint(arg0.getX(),
//				arg0.getY());
//		SelectTool tool = new SelectTool(panel);
//		comp.select(tool);
//		PaintComponent point = tool.getSelectedComponents().get(0);
//		switch (state) {
//		case DEFAULT:
//
//			if (point instanceof SimplePoint) {
//				fromPoint = (SimplePoint) point;
//				this.setState(State.SINGLE_POINT_SELECTED);
//			}
//			break;
//		case SINGLE_POINT_SELECTED:
//			if (point instanceof SimplePoint) {
//				toPoint = (SimplePoint) point;
//				this.setState(State.DEFAULT);
//				ConstructLineSegmentGlobalAction constructLineSegment = (ConstructLineSegmentGlobalAction) ActionName.CONSTRUCT_LINE_SEGMENT_ACTION.getAssiciatedAction();
//				constructLineSegment.setFromPoint(fromPoint);
//				constructLineSegment.setToPoint(toPoint);
//				GlobalPaintActionExecuter.getSharedInstance().execute(constructLineSegment, panel);
//				
//			} 
//			else if (point == null){
//				this.setState(State.DEFAULT);
//			}
//			
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void start(PaintPanel panel) {
//		this.panel = panel;
//		setState(State.DEFAULT);
//	}
//
//	@Override
//	public void reset() {
//		setState(State.DEFAULT);
//	}
//
//}
