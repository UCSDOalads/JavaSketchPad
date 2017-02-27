package actions;

import javax.swing.JOptionPane;

import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

public class AddTextBoxAction extends PaintAction {

	public AddTextBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String s = JOptionPane.showInputDialog("Please enter the text to display");
		panel.addPaintComponent(new TextPaintComponent(s, panel.getWidth() / 2, panel.getHeight()/2));
		panel.repaint();
	}

	@Override
	public String locationString() {
		return "Add/Text Box...";
	}

}
