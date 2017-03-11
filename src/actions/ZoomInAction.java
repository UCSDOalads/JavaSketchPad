package actions;

import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;

public class ZoomInAction extends ZoomAction {
		
	public ZoomInAction (PaintPanel panel) {
		super(panel);
		this.setZoomValue(0.5);
	}

	@Override
	public void performAction() {
		super.performAction();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Zoom_In().toString();
	}
}
