package painttools.tools;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import buttons.ToolButton;
import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import ui.PaintPanel;

public class LineTool extends PaintTool {

	LineSegment line;
	private PaintPanel panel;

	@Override
	public ToolButton getButton() {

		ToolButton b = new ToolButton();
		
		
		ImageIcon icon = new ImageIcon("./images/line.png");
		b.setOriginalImage(icon);
		
		
		ImageIcon icon2 = new ImageIcon("./images/lineselected.png");
		b.setSelectedImage(icon2);
		return b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// If no point is selected, select the point(if any) at the mouse's
		// location.
		if (panel.getSelectTool().getSelectedComponents().size() == 0) {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());

			if (comp instanceof SimplePoint) {
				panel.getSelectTool().selectComponent(comp);
			}
		}

		// If 1 point has been selected, select the point(if any) at the mouse's
		// location and draw the line segment.
		else {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());

			if (comp instanceof SimplePoint) {
				ArrayList<PaintComponent> items = panel.getSelectTool()
						.getSelectedComponents();

				ConstructLineSegmentGlobalAction associatedAction = (ConstructLineSegmentGlobalAction) ActionName.CONSTRUCT_LINE_SEGMENT_ACTION
						.getAssiciatedAction();

				associatedAction.setFromPoint((SimplePoint) (items.get(0)));
				associatedAction.setToPoint((SimplePoint) (comp));
				GlobalPaintActionExecuter.getSharedInstance().execute(
						associatedAction, panel);

				// change selection
				panel.getSelectTool().clearSelection();
				panel.repaint();
				panel.setTempComponent(null);
				panel.showCursor();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Discard the line segment when right click on space that is not
		// occupied by a SimplePoint.
		if (SwingUtilities.isRightMouseButton(e)) {
			if (!(panel.componentUnderPoint(e.getX(), e.getY()) instanceof SimplePoint)) {
				panel.getSelectTool().clearSelection();
				panel.setTempComponent(null);
				panel.showCursor();
				panel.repaint();
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		// If one point has already been selected, draw a line segment between
		// that point and the current point of the mouse as mouse moves.
		if (panel.getSelectTool().getSelectedComponents().size() == 1) {
			panel.hideCursor();

			if (panel.componentUnderPoint(e.getX(), e.getY()) instanceof SimplePoint) {
				panel.showCursor();
			}

			SimplePoint currPoint = new SimplePoint(e.getX(), e.getY());
			line = new LineSegment((SimplePoint) panel.getSelectTool()
					.getSelectedComponents().get(0), currPoint);
			
			panel.setTempComponent(line);
			panel.repaint();

		}
	}

	@Override
	public void start(PaintPanel panel) {
		this.panel = panel;

		panel.getSelectTool().clearSelection();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
