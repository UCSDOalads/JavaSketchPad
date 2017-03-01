package actions;

import java.lang.reflect.Constructor;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class AddLazyJavaConstructorAction extends PaintAction {

	public AddLazyJavaConstructorAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof ClassPaintComponent) {
			return true;
		}
		return false;
	}

	@Override
	public void performAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);
		Constructor[] cons = comp.getDisplayingClass().getConstructors();

		int desiaredConstructorIndex = Integer
				.parseInt(JOptionPane.showInputDialog(
						"Please enter the index of the constructor you would like to use: \n\n\n"
								+ getConstructorsSelectionUI(cons)));
		ClassConstructorPaintComponent consComp = new ClassConstructorPaintComponent(
				cons[desiaredConstructorIndex], panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(consComp);
		panel.repaint();

	}

	public String getConstructorsSelectionUI(Constructor[] cons) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cons.length; i++) {
			Constructor constructor = cons[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Constructor().toString();
	}

}
