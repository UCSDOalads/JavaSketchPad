package actions;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.DataDisplayPaintComponent;
import ui.PaintPanel;

public class AddDataDisplayBoxAction extends PaintAction {

	public AddDataDisplayBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		DataDisplayPaintComponent comp = new DataDisplayPaintComponent("Data Display", panel.getWidth() /2, panel.getHeight()/2);
		panel.addPaintComponent(comp);
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Display_Box().Add().toString();
	}

}
