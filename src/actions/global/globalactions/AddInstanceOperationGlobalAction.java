package actions.global.globalactions;

import java.lang.reflect.Constructor;

import actions.global.GlobalPaintAction;
import paintcomponents.java.interactive.InstanceOperationComponent;
import ui.PaintPanel;

public class AddInstanceOperationGlobalAction extends GlobalPaintAction {

	private Constructor constructorToSet;
	private InstanceOperationComponent insComp;

	@Override
	protected void execute(PaintPanel panel) {
		insComp = new InstanceOperationComponent(constructorToSet,
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(insComp);
		//TODO: add action to undo redo manager
		panel.repaint();
	}

	public void setConstructorToSet(Constructor constructorToSet) {
		this.constructorToSet = constructorToSet;
	}

	public InstanceOperationComponent getInsComp() {
		return insComp;
	}
}
