package painttools.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import buttons.ToolButton;
import paintcomponents.SimplePoint;
import ui.PaintPanel;
import ui.icons.CustomIcons;

public class DotTool implements PaintToolsInterface {

	SimplePoint p;
	private PaintPanel panel;
	private ToolButton button;

	public DotTool(){
		createButton();
	}
	@Override
	public void start(PaintPanel panel) {
		this.panel = panel;

		panel.hideCursor();
		p = new SimplePoint(0, 0);
		panel.setTempComponent(p);

	}
	@Override
	public void createButton() {
		button = new ToolButton();
		button.setOriginalImage(CustomIcons.dot());
		button.setSelectedImage(CustomIcons.selectedDot());
		
	}
	@Override
	public ToolButton getButton() {

		return button;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		SimplePoint pointToAdd = new SimplePoint(p);

		// change selection to be only the added point
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(pointToAdd);

		panel.addPaintComponent(pointToAdd);
		panel.toolSelected(panel.getSelectTool());

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
		p.setX(e.getX());
		p.setY(e.getY());
		panel.repaint();
	}

	@Override
	public void reset() {
		// do nothing on reset
		// temporary component will automatically be removed

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
