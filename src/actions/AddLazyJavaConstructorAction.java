package actions;

import java.lang.reflect.Constructor;

import javax.swing.JOptionPane;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaConstructorGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class AddLazyJavaConstructorAction extends MenuBarPaintAction {

	public AddLazyJavaConstructorAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents().get(0) instanceof ClassPaintComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);

		AddLazyJavaConstructorGlobalAction assiciatedAction = (AddLazyJavaConstructorGlobalAction) ActionName.ADD_LAZY_JAVA_CONSTRUCTOR_ACTION
				.getAssiciatedAction();
		assiciatedAction.setComponent(comp);
		int desiaredConstructorIndex = Integer
				.parseInt(JOptionPane
						.showInputDialog("Please enter the index of the constructor you would like to use: \n\n\n"
								+ getConstructorsSelectionUI(assiciatedAction
										.getConstructor())));
		assiciatedAction.setConstructorIndex(desiaredConstructorIndex);
		assiciatedAction.setCoord(panel.getWidth() / 2, panel.getHeight() / 2);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Constructor().toString();
	}

	public String getConstructorsSelectionUI(Constructor[] cons) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cons.length; i++) {
			Constructor constructor = cons[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();

	}
}
