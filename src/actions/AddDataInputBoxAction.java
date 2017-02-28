package actions;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

public class AddDataInputBoxAction extends PaintAction {

	public AddDataInputBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		DataInputTextfieldPaintComponent comp = new DataInputTextfieldPaintComponent("Data Input", panel.getWidth() /2, panel.getHeight()/2);
		panel.addPaintComponent(comp);
		panel.repaint();

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Input_Box().Add().toString();
	}

}
