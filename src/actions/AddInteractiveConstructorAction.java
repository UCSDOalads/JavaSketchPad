package actions;

import java.lang.reflect.Constructor;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.interactive.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class AddInteractiveConstructorAction extends PaintAction {

	public AddInteractiveConstructorAction(PaintPanel panel) {
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
		// add action to undo redo manager
//		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
//					
//			@Override
//			public void undoAction() {
//				consComp.remove(panel);
//				panel.repaint();
//			}
//					
//			@Override
//			public void redoAction() {
//				panel.addPaintComponent(consComp);
//				panel.repaint();
//			}
//		});
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
		return ActionsMenuBarTitles.Developer("Interactive/Constructor").toString();
	}

}
