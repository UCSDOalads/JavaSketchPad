package actions;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;

import paintcomponents.java.interactive.ClassConstructorPaintComponent;
import paintcomponents.java.interactive.MethodPaintComponent;
import ui.PaintPanel;

public class AddInteractiveJavaMethodComponentAction extends PaintAction {

	public AddInteractiveJavaMethodComponentAction(PaintPanel panel) {
		super(panel);
	}

	//TODO
	//NOTE: I am copying from Constructor, consider refinement

	@Override
	public boolean canPerformAction() {
		if (panel.getSelectTool().getSelectedComponents().size() != 1) {
			return false;
		}
		if (panel.getSelectTool().getSelectedComponents()
				.get(0) instanceof ClassConstructorPaintComponent) {
			return true;
		}
		return false;
	}

	
	@Override
	public void performAction() {
		ClassConstructorPaintComponent comp = (ClassConstructorPaintComponent) panel.getSelectTool()
				.getSelectedComponents().get(0);
		Method[] methods = comp.getDisplayingClass().getMethods();

		
		
		
		int desiaredConstructorIndex = Integer
				.parseInt(JOptionPane.showInputDialog(
						"Please enter the index of the constructor you would like to use: \n\n\n"
								+ getMethodsSelectionUI(methods)));
		MethodPaintComponent methodComp = new MethodPaintComponent(
				methods[desiaredConstructorIndex], comp.getInstance(), panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(methodComp);
		// add action to undo redo manager
//		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
//					
//			@Override
//			public void undoAction() {
//				methodComp.remove(panel);
//				panel.repaint();
//			}
//					
//			@Override
//			public void redoAction() {
//				panel.addPaintComponent(methodComp);
//				panel.repaint();
//			}
//		});
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
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Developer("Interactive/Method").toString();
	}

}
