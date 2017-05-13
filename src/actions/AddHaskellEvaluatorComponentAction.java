package actions;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.haskell.EvaluateHaskellPaintComponent;
import ui.PaintPanel;

public class AddHaskellEvaluatorComponentAction extends MenuBarPaintAction {

	public AddHaskellEvaluatorComponentAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		EvaluateHaskellPaintComponent comp = new EvaluateHaskellPaintComponent("Use Data Display/Update to compute expression result", panel.getWidth() /2, panel.getHeight()/2);
		panel.addPaintComponent(comp);
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Haskell/Add Evaluator").toString();
	}

}
