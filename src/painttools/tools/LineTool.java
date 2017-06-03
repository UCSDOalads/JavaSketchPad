package painttools.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ConstructDataLineSegmentGlobalAction;
import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import buttons.ToolButton;
import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import paintcomponents.TextPaintComponent;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataTextPaintComponent;
import paintcomponents.data.DataToPoint;
import ui.PaintPanel;
import ui.icons.CustomIcons;

public class LineTool implements PaintToolsInterface {

	LineSegment line;
	private PaintPanel panel;
	private ToolButton button;

	public LineTool(){

		createButton();
	}
	@Override
	public void createButton() {
		// TODO Auto-generated method stub

		button = new ToolButton();
		button.setOriginalImage(CustomIcons.line());
		button.setSelectedImage(CustomIcons.selectedLine());
		
	}
	
	@Override
	public ToolButton getButton() {
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

			// If mouse is on a SimplePoint, add it to the select tool.
			if (comp instanceof SimplePoint) {
				panel.getSelectTool().selectComponent(comp);
			}

			// Otherwise only select DataFromPoints.
			else if (comp instanceof DataTextPaintComponent) {
				panel.getSelectTool().mousePressed(e);

				if (items.size() == 1
						&& (items.get(0) instanceof DataToPoint || items.get(0) instanceof TextPaintComponent)) {
					panel.getSelectTool().clearSelection();
				}
			}
		}

		// If 1 point has been selected, check condition, select the point(if
		// any) at the mouse's location and draw the line segment.
		else {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
			boolean canPaint = false;

			// If comp is a DataTextPaintComponent, use select tool to get the
			// part of it that is selected and set that part as comp.
			if (comp instanceof DataTextPaintComponent) {
				panel.getSelectTool().mousePressed(e);

				// Handle the case where the point clicked has been selected
				// (mousePressed does not change number of selected components).
				if (items.size() == 2) {
					comp = items.get(1);
					panel.getSelectTool().deselectComponent(items.get(1));
				}
			}

			// If the component under mouse is a SimplePoint, is different from
			// the selected point, and there is no lines between them, set
			// canPaint to true.
			if (comp instanceof SimplePoint && comp != items.get(0)) {

				canPaint = true;
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
			}

			if (canPaint) {

				// If both of the points are strictly SimplePoint(not data
				// points), draw a line segment between them.
				if ((comp.getClass() == SimplePoint.class)
						&& (items.get(0).getClass() == SimplePoint.class)) {

					ConstructLineSegmentGlobalAction associatedAction = (ConstructLineSegmentGlobalAction) ActionName.CONSTRUCT_LINE_SEGMENT_ACTION
							.getAssociatedAction();

					associatedAction.setFromPoint((SimplePoint) items.get(0));
					associatedAction.setToPoint((SimplePoint) comp);
					GlobalPaintActionExecuter.getSharedInstance().execute(
							associatedAction, panel);

					// Clear selection and repaint panel.
					panel.getSelectTool().clearSelection();
					panel.repaint();
					panel.setTempComponent(null);
					panel.showCursor();
					panel.setDefaultSelectTool();
				}

				// If the first point is dataFrom point and the point under
				// cursor is dataToPoint, draw the line.
				else if ((comp.getClass() == DataToPoint.class)
						&& (items.get(0).getClass() == DataFromPoint.class)) {

					ConstructDataLineSegmentGlobalAction associatedAction = (ConstructDataLineSegmentGlobalAction) ActionName.CONSTRUCT_DATA_LINE_SEGMENT_ACTION
							.getAssociatedAction();

					associatedAction.setFromPoint((DataFromPoint) items.get(0));
					associatedAction.setToPoint((DataToPoint) comp);
					GlobalPaintActionExecuter.getSharedInstance().execute(
							associatedAction, panel);

					// Clear selection and repaint panel.
					panel.getSelectTool().clearSelection();
					panel.repaint();
					panel.setTempComponent(null);
					panel.showCursor();
					panel.setDefaultSelectTool();
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
				panel.setDefaultSelectTool();
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
		// that point and the current point of the mouse as mouse moves. The
		// cursor is hidden when it is not on a point or on the selected point.
		if (panel.getSelectTool().getSelectedComponents().size() == 1) {
			panel.hideCursor();

			if (panel.componentUnderPoint(e.getX(), e.getY()) instanceof SimplePoint
					&& panel.componentUnderPoint(e.getX(), e.getY()) != panel
							.getSelectTool().getSelectedComponents().get(0)) {
				panel.showCursor();
			}

			// If the component under cursor is a DataTextPaintComponent, first
			// get its part under cursor.
			if (panel.componentUnderPoint(e.getX(), e.getY()) instanceof DataTextPaintComponent) {
				panel.getSelectTool().mousePressed(e);

				// Do not display cursor when the point under cursor has already
				// been selected (mousePressed does not change number of
				// selected components).
				if (panel.getSelectTool().getSelectedComponents().size() == 2) {

					// Determine if the part under cursor is a SimplePoint, show
					// cursor if true.
					if (panel.getSelectTool().getSelectedComponents().get(1) instanceof SimplePoint)
						panel.showCursor();

					// Deselect the component.
					panel.getSelectTool().deselectComponent(
							panel.getSelectTool().getSelectedComponents()
									.get(1));
				}
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
