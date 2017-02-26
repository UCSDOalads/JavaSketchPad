package actions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import paintcomponents.DataInputTextfieldPaintComponent;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class InputDataForDataInputBoxAction extends PaintAction {

	public InputDataForDataInputBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		ArrayList<PaintComponent> comps = panel.getSelectTool().getSelectedComponents();
		if(comps.size()!= 1) return false;
		if(comps.get(0) instanceof DataInputTextfieldPaintComponent){
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		DataInputTextfieldPaintComponent inputComp = (DataInputTextfieldPaintComponent) panel.getSelectTool().getSelectedComponents().get(0);
		String s = JOptionPane.showInputDialog("Please specify the message to push to the data input");
		inputComp.inputData(s);
		panel.repaint();
	}

	@Override
	public String locationString() {
		return "Input/Input into Data Panel";
	}

}
