package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import paintcomponents.haskell.EvaluateHaskellPaintComponent;
import ui.PaintPanel;

public class AddHaskellEvaluatorComponentGlobalAction
		extends GlobalPaintAction {

	// currently cannot be evaluated by creating the line seg
	// Error: typesystem.HaskellType cannot be cast to typesystem.JavaType

	private Class classToCreate;

	/**
	 * @param classToCreate
	 *            the classToCreate to set
	 */
	public void setClassToCreate(Class classToCreate) {
		this.classToCreate = classToCreate;
	}

	@Override
	protected void execute(PaintPanel panel) {

		EvaluateHaskellPaintComponent comp = new EvaluateHaskellPaintComponent(
				"Use Data Display/Update to compute expression result", panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);
		panel.repaint();
	}

}
