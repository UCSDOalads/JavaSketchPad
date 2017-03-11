package painttools.tools;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JPopupMenu;

import actions.ZoomInAction;
import actions.ZoomOutAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class SmartTool extends PaintTool implements SelectToolInterface{
	
	private PaintPanel panel;
	private MouseEvent lastMouseEvent;

	public SmartTool(PaintPanel panel) {
		super();
		this.panel = panel;
		selectedComponents = new ArrayList<>();
		listeners = new ArrayList<>();

	}

	private ArrayList<PaintComponent> selectedComponents;
	private ArrayList<SelectionToolListener> listeners;
	@Override
	public ArrayList<PaintComponent> getSelectedComponents() {
		return selectedComponents;
	}

	@Override
	public void selectComponent(PaintComponent comp) {
		comp.select(this);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();
	}

	@Override
	public void deselectComponent(PaintComponent comp) {
		comp.deselect(this);
		for (SelectionToolListener selectionToolListener : listeners) {
			selectionToolListener.selectionChanged();
		}
		panel.repaint();		
	}

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

	public boolean addSelectionToolListener(SelectionToolListener e) {
		return listeners.add(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		lastMouseEvent = e;
		if(e.getButton() == MouseEvent.BUTTON3) {
			PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
			if( comp != null) {
				selectComponent(comp);
				JPopupMenu menu = MenuForPaintComponent.getMenuForPaintComponent(comp, panel);
				//menu.setLocation(e.getPoint());
				//menu.setPreferredSize(new Dimension(200,200));
				menu.show(panel, e.getX(), e.getY());
				//panel.add(menu);
				panel.repaint();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(PaintPanel panel) {
		// TODO Auto-generated method stub
		panel.setSelectTool(this);

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public MouseEvent getLastMouseEvent() {
		return lastMouseEvent;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		ZoomInAction zoomIn = new ZoomInAction(panel);
		ZoomOutAction zoomOut = new ZoomOutAction(panel);
		if (e.getWheelRotation() > 0) {
			zoomIn.setCenterX(e.getX());
			zoomIn.setCenterY(e.getY());
			zoomIn.performAction();
		} else {
			zoomOut.setCenterX(e.getX());
			zoomOut.setCenterY(e.getY());
			zoomOut.performAction();
		}
	}

}
