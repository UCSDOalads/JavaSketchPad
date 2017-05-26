package actions;

import ui.PaintPanel;
import actions.menu.ActionsMenuBarTitles;

public class ZoomOutAction extends ZoomAction {

	public ZoomOutAction (PaintPanel panel) {
		super(panel);
		this.setZoomValue(-0.1);
	}

	@Override
	public void performAction() {
		super.performAction();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Zoom_Out().toString();
	}

}
