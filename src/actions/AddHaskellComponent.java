package actions;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.haskell.HaskellExpressionPaintComponent;
import ui.PaintPanel;

public class AddHaskellComponent extends PaintAction {

	public AddHaskellComponent(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String expr = JOptionPane
				.showInputDialog("Please enter the haskell expression");
		panel.addPaintComponent(new HaskellExpressionPaintComponent(
				expr, panel.getWidth() / 2, panel.getHeight() / 2));
		
		panel.repaint();

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Haskell Expression")
				.toString();
	}

}
