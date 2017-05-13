package painttools.tools;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import paintcomponents.data.DataToPoint;
import ui.PaintPanel;

public class LineTool extends PaintTool {

	LineSegment line;
	private PaintPanel panel;

	@Override
	public JButton getButton() {
		JButton button = super.getButton();
		button.setIcon(new Icon() {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				g.fillRect(10, 20, 40, 8);

			}

			@Override
			public int getIconWidth() {
				return 40;
			}

			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return 40;
			}
		});
		return button;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// If no point is selected, select the point(if any) at the mouse's
		// location.
		ArrayList<PaintComponent> items = panel.getSelectTool()
				.getSelectedComponents();
		
		if (items.size() == 0) {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());

			//System.out.println(comp instanceof DataToPoint);
			if (comp instanceof SimplePoint && !(comp instanceof DataToPoint)) {
				panel.getSelectTool().selectComponent(comp);
			}
		}

		// If 1 point has been selected, check condition, select the point(if
		// any) at the mouse's
		// location and draw the line segment.
		else {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());

			// If the component under mouse is a SimplePoint and is not the
			// previously selected point.
			if (comp instanceof SimplePoint && comp != items.get(0)) {

				boolean canPaint = true;
				// Check if line segment already exists.
				ArrayList<PaintComponent> components = panel
						.getPaintComponents();
				LineSegment testLine = null;

				// get all paintComponents
				for (PaintComponent paintComponent : components) {
					if (paintComponent instanceof LineSegment) {
						testLine = (LineSegment) paintComponent;
						// check front point and to point similarities
						if (items.get(0) == testLine.getFromPoint()
								&& comp == testLine.getToPoint())
							canPaint = false;
						if (comp == testLine.getFromPoint()
								&& items.get(0) == testLine.getToPoint())
							canPaint = false;
					}
				}

				if (canPaint) {
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
