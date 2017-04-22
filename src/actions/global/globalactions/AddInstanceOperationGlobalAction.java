package actions.global.globalactions;

import java.lang.reflect.Constructor;

import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;
import actions.global.GlobalPaintAction;

public class AddInstanceOperationGlobalAction extends GlobalPaintAction {

	private Constructor constructorToSet;
	private InstanceOperationComponent insComp;

	@Override
	protected void execute(PaintPanel panel) {
		insComp = new InstanceOperationComponent(constructorToSet,
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(insComp);
		panel.repaint();
	}

	public void setConstructorToSet(Constructor constructorToSet) {
		this.constructorToSet = constructorToSet;
	}

	public InstanceOperationComponent getInsComp() {
		return insComp;
	}
}
