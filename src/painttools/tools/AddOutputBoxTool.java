package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import ui.PaintPanel;
import actions.AddDataDisplayBoxAction;
import buttons.ToolButton;

public class AddOutputBoxTool extends PaintTool {

	private ToolButton button;

	public AddOutputBoxTool(PaintPanel panel) {
		button = new ToolButton("Add Output Box");

		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (action.canPerformAction()) {
					action.performAction();
					panel.toolSelected(panel.getSelectTool());
				}

			}
		});
	}

	@Override
	public void start(PaintPanel panel) {
	}

	@Override
	public ToolButton getButton() {
		return button;
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
