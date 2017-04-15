package actions.global.globalactions;

import java.lang.reflect.Constructor;

import javax.swing.JOptionPane;

import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

public class AddLazyJavaConstructorGlobalAction extends GlobalPaintAction {

	private ClassPaintComponent comp;

	@Override
	protected void execute(PaintPanel panel) {
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
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
					
			@Override
			public void undoAction() {
				consComp.remove(panel);
				panel.repaint();
			}
					
			@Override
			public void redoAction() {
				panel.addPaintComponent(consComp);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "add lazy javaConstructor";
			}

			@Override
			protected String commandDescription() {
				return "add a lazily evaludated java constructor";
			}
		});
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
	
	public void setComponent(ClassPaintComponent comp) {
		this.comp = comp;
	}
}
