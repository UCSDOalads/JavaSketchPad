package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import ui.PaintPanel;
import actions.AddLazyJavaClassAction;
import buttons.ToolButton;

public class AddClassTool extends PaintTool {

	private ToolButton button;
	private PaintPanel panel;

	public AddClassTool(PaintPanel panel) {
		this.panel = panel;
		button = getButton();


	}

	@Override
	public void start(PaintPanel panel) {
	}

	@Override
	public ToolButton getButton() {
		ToolButton b = new ToolButton();
		
		

		ImageIcon icon = new ImageIcon("./images/dot.png");
		b.setOriginalImage(icon);

		ImageIcon icon2 = new ImageIcon("./images/dotselected.png");
		b.setSelectedImage(icon2);
		AddLazyJavaClassAction action = new AddLazyJavaClassAction(panel);

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (action.canPerformAction()) {
					action.performAction();
					panel.toolSelected(panel.getSelectTool());
				}

			}
		});
		return b;
	}

	@Override
	public void reset() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

}
