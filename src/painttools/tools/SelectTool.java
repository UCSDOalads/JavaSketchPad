package painttools.tools;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.colorchooser.DefaultColorSelectionModel;

import icons.LeftArrow;
import paintcomponents.PaintComponent;
import settings.Defaults;
import ui.PaintPanel;

public class SelectTool extends PaintTool {

	private PaintPanel panel;

	private ArrayList<PaintComponent> selectedComponents;
	private ArrayList<SelectionToolListener> listeners;

	/**
	 * adds a selection listener
	 * 
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addSelectionToolListener(SelectionToolListener e) {
		return listeners.add(e);
	}

	/**
	 * @return the selectedComponents
	 */
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
		// select or deselect component under cursor on mouse clicked

		PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
		if (comp != null) {
			if (comp.isSelected()) {
				deselectComponent(comp);
			} else {
				selectComponent(comp);
			}

		}

	}

	/**
	 * Selects a component, changes selection
	 * All listeners are informed.
	 * Panel are repainted
	 * @param comp
	 */
	public void selectComponent(PaintComponent comp) {
		comp.select();
		selectedComponents.add(comp);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
	}
	
	/**
	 * Deselect a component, changes selection
	 * All listeners are informed.
	 * Panel are repainted
	 * @param comp
	 */
	public void deselectComponent(PaintComponent comp){
		comp.deselect();
		selectedComponents.remove(comp);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
		
	}
	
	/**
	 * Deselect ALL components, changes selection
	 * All listeners are informed.
	 * Panel are repainted
	 */
	public void clearSelection(){
		// remove all selection
		for (PaintComponent component : selectedComponents) {
			component.deselect();
		}
		selectedComponents.clear();
		
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
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

}
