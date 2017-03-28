package script;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

public class InterpreterAddLazy {

	private PaintPanel panel;

	public InterpreterAddLazy(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case "JavaClass":
				performAddJavaClassAction();
				break;

			case "JavaConstructor":
				performAddJavaConstructorAction();
				break;

			case "JavaFieldsComponent":
				performAddJavaFieldsComponentAction();
				break;

			case "JavaMethodComponent":
				performAddJavaMethodComponentAction();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performAddJavaClassAction() {
		String className = JOptionPane
				.showInputDialog("Please specify the name of the Java Class");
		try {
			Class classObj = Class.forName(className);
			ClassPaintComponent comp = new ClassPaintComponent(classObj,
					panel.getWidth() / 2, panel.getHeight() / 2);
			panel.addPaintComponent(comp);
			// add action to undo redo manager
			SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
					new UndoRedoableInterface() {

						@Override
						public void undoAction() {
							comp.remove(panel);
							panel.repaint();
						}

						@Override
						public void redoAction() {
							panel.addPaintComponent(comp);
							panel.repaint();
						}
					});
			panel.repaint();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, className + " :: Class Not Found");
		}
	}

	private void performAddJavaConstructorAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);
		Constructor[] cons = comp.getDisplayingClass().getConstructors();

		int desiaredConstructorIndex = Integer
				.parseInt(JOptionPane
						.showInputDialog("Please enter the index of the constructor you would like to use: \n\n\n"
								+ getConstructorsSelectionUI(cons)));
		ClassConstructorPaintComponent consComp = new ClassConstructorPaintComponent(
				cons[desiaredConstructorIndex], panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(consComp);
		// add action to undo redo manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

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
				});
		panel.repaint();
	}

	private String getConstructorsSelectionUI(Constructor[] cons) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cons.length; i++) {
			Constructor constructor = cons[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();
	}

	private void performAddJavaFieldsComponentAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);

		FieldsPaintComponent fieldsComp = new FieldsPaintComponent(
				comp.getDisplayingClass(), panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(fieldsComp);
		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

					@Override
					public void undoAction() {
						fieldsComp.remove(panel);
					}

					@Override
					public void redoAction() {
						panel.addPaintComponent(fieldsComp);

					}
				});
		panel.repaint();
	}

	private void performAddJavaMethodComponentAction() {
		ClassPaintComponent comp = (ClassPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);
		Method[] methods = comp.getDisplayingClass().getMethods();

		int desiaredConstructorIndex = Integer
				.parseInt(JOptionPane
						.showInputDialog("Please enter the index of the constructor you would like to use: \n\n\n"
								+ getMethodsSelectionUI(methods)));
		MethodPaintComponent methodComp = new MethodPaintComponent(
				methods[desiaredConstructorIndex], panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(methodComp);
		// add action to undo redo manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

					@Override
					public void undoAction() {
						methodComp.remove(panel);
						panel.repaint();
					}

					@Override
					public void redoAction() {
						panel.addPaintComponent(methodComp);
						panel.repaint();
					}
				});
		panel.repaint();
	}

	public String getMethodsSelectionUI(Method[] methods) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < methods.length; i++) {
			Method constructor = methods[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();
	}
}
