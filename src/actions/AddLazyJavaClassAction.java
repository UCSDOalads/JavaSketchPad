package actions;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import actions.menu.PaintActionMenuItem;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class AddLazyJavaClassAction extends PaintAction {

	public AddLazyJavaClassAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String className = JOptionPane
				.showInputDialog("Please specify the name of the Java Class");
		try {
			Class classObj = Class.forName(className);
			panel.addPaintComponent(new ClassPaintComponent(classObj,
					panel.getWidth() / 2, panel.getHeight() / 2));
			panel.repaint();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel,
					className + " :: Class Not Found");
		}

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
