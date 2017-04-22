package actions.global.globalactions;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

public class AddLazyJavaMethodComponentGlobalAction extends GlobalPaintAction {
	private ClassPaintComponent comp;
	private Method methodToSet;
	@Override
	protected void execute(PaintPanel panel) {

		
		MethodPaintComponent methodComp = new MethodPaintComponent(
				methodToSet, panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(methodComp);
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
		

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

			@Override
			protected String commandName() {
				return "add lazy javaMethodComponent";
			}

			@Override
			protected String commandDescription() {
				return "add a lazily evaluated java method component";
			}
		});
		panel.repaint();
	}
	public void setMethod(Method methodToSet){
		this.methodToSet = methodToSet;
	}
	
	public void setMethodComponent(ClassPaintComponent comp){
		this.comp = comp;
	}
	
	public ClassPaintComponent getComp(){
		return comp;
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
