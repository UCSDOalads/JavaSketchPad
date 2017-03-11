package painttools.tools;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.colorchooser.DefaultColorSelectionModel;

import icons.LeftArrow;
import paintcomponents.PaintComponent;
import settings.Defaults;
import ui.PaintPanel;

public class SelectTool extends PaintTool implements SelectToolInterface{

	private PaintPanel panel;

	private ArrayList<PaintComponent> selectedComponents;
	private ArrayList<SelectionToolListener> listeners;
	
	private MouseEvent lastMouseEvent;

	/**
	 * @return the lastMouseEvent
	 */
	@Override
	public MouseEvent getLastMouseEvent() {
		return lastMouseEvent;
	}

	/**
	 * adds a selection listener
	 * 
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean addSelectionToolListener(SelectionToolListener e) {
		return listeners.add(e);
	}

	/* (non-Javadoc)
	 * @see painttools.tools.SelectToolInterface#getSelectedComponents()
	 */
	@Override
	public ArrayList<PaintComponent> getSelectedComponents() {
		return selectedComponents;
	}

	public SelectTool(PaintPanel panel) {
		selectedComponents = new ArrayList<>();
		listeners = new ArrayList<>();
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see painttools.tools.SelectToolInterface#selectComponent(paintcomponents.PaintComponent)
	 */
	@Override
	public void selectComponent(PaintComponent comp) {
		comp.select(this);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
	}

	/* (non-Javadoc)
	 * @see painttools.tools.SelectToolInterface#deselectComponent(paintcomponents.PaintComponent)
	 */
	@Override
	public void deselectComponent(PaintComponent comp) {
		comp.deselect(this);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();

	}

	/* (non-Javadoc)
	 * @see painttools.tools.SelectToolInterface#clearSelection()
	 */
	@Override
	public void clearSelection() {
		// remove all selection
		while(!selectedComponents.isEmpty()) {
			selectedComponents.get(0).deselect(this);
		}

		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastMouseEvent = e;
		// TODO Auto-generated method stub
		// select or deselect component under cursor on mouse clicked


		PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
		if (comp != null) {
			if (comp.isSelected()) {
				// do not deselect on mouse press, deselect in mouse release
				movingComponentOriginallySelected = true;
			} else {
				selectComponent(comp);
				movingComponentOriginallySelected = false;
			}
			canDrag = true;

		} else {
			clearSelection();
			canDrag = false;
		}

		lastX = e.getX();
		lastY = e.getY();
		moved = false;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// select or deselect component under cursor on mouse clicked
		lastMouseEvent = e;

		PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
		if (comp != null) {
			if (comp.isSelected()) {
				// deselect if mouse not moved
				if (!moved && movingComponentOriginallySelected) {
					deselectComponent(comp);
				}
			} else {
				// do not select on mouse release
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

	// lastX and Y position for mouse
	private int lastX, lastY;
	private boolean moved;
	private boolean canDrag;

	private boolean movingComponentOriginallySelected;// whether the currently
														// moving component has
														// originally been
														// seleceted

	@Override
	public void mouseDragged(MouseEvent e) {

		lastMouseEvent = e;
		// do nothing if dragged empty
		// TODO GROUP SELECTION WHEN CANNOT DRAG
		if (!canDrag)
			return;
		// if it is the first time in our move, and the moving component is not
		// originally selected, deselect all bottom components
		if (!moved && !movingComponentOriginallySelected) {
			// deselect the bottom components, by removing all until
			while (selectedComponents.size() != 1) {
				deselectComponent(selectedComponents.get(0));
			}
		}
		for (PaintComponent comp : selectedComponents) {
			comp.translate(e.getX() - lastX, e.getY() - lastY);

			// show arrow as dragging
			panel.hideCursor();
			// get the left arrow
			PaintComponent arrow = LeftArrow.paintComponentFromPolygon(
					LeftArrow.getPolygon(),
					Defaults.sharedDefaults().defaultColorForSelectToolIcon());
			arrow.setX(e.getX());
			arrow.setY(e.getY());

			// add arrow by setting the temp component
			panel.setTempComponent(arrow);

			panel.repaint();
		}

		lastX = e.getX();
		lastY = e.getY();
		moved = true;
		panel.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		lastMouseEvent = e;
		// shows a left arrow if cursor hits something
		PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
		if (comp != null) {

			panel.hideCursor();
			// get the left arrow
			PaintComponent arrow = LeftArrow.paintComponentFromPolygon(
					LeftArrow.getPolygon(),
					Defaults.sharedDefaults().defaultColorForSelectToolIcon());
			arrow.setX(e.getX());
			arrow.setY(e.getY());

			// add arrow by setting the temp component
			panel.setTempComponent(arrow);

			panel.repaint();

		} else {
			// remove arrow
			panel.setTempComponent(null);
			panel.showCursor();
			panel.repaint();
		}

	}

	@Override
	public void start(PaintPanel panel) {
		this.panel = panel;
		panel.setSelectTool(this);
	}

	@Override
	public JButton getButton() {
		JButton button = super.getButton();
		button.setIcon(LeftArrow.iconFromPolygon(LeftArrow.getPolygon(),
				Defaults.sharedDefaults().defaultColorForSelectToolIcon()));
		return button;
	}

	@Override
	public void reset() {

		clearSelection();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
