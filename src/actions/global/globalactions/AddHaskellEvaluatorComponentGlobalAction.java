package actions.global.globalactions;

import paintcomponents.haskell.EvaluateHaskellPaintComponent;
import ui.PaintPanel;
import actions.global.GlobalPaintAction;

public class AddHaskellEvaluatorComponentGlobalAction
		extends GlobalPaintAction {

	// currently cannot be evaluated by creating the line seg
	// Error: typesystem.HaskellType cannot be cast to typesystem.JavaType

	@Override
	protected void execute(PaintPanel panel) {

		EvaluateHaskellPaintComponent comp = new EvaluateHaskellPaintComponent(
				"Use Data Display/Update to compute expression result", panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);
		panel.repaint();
	}

}
