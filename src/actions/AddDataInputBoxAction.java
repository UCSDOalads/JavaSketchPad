package actions;

import paintcomponents.DataInputTextfieldPaintComponent;
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
		return "Add/Data Input Box...";
	}

}
