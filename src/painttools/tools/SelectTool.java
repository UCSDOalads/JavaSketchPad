package painttools.tools;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class SelectTool extends PaintTool {

	private PaintPanel panel;
	
	private ArrayList<PaintComponent> selectedComponents;
	
	public SelectTool() {
		selectedComponents = new ArrayList<>();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		PaintComponent comp = panel.componentUnderPoint(e.getX(), e.getY());
		if(comp != null){
			if(comp.isSelected()){
				comp.deselect();
				selectedComponents.remove(comp);
			} else {
				comp.select();
				selectedComponents.add(comp);
			}
			panel.repaint();
			
		}

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
		// TODO Auto-generated method stub

	}

	@Override
	public void start(PaintPanel panel) {
		this.panel = panel;

	}

}
