package script;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import paintcomponents.PaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class InterpreterAddLazyJavaClass {

	private PaintPanel panel;
	
	public InterpreterAddLazyJavaClass(Tokenizer tokenizer, PaintPanel panel)
		throws ExecutionErrorException {
		this.panel = panel;
		if (tokenizer.hasNext()) {
			ComponentMap.map.put(tokenizer.next(), performAddJavaClassAction());
		}
	}

	private PaintComponent performAddJavaClassAction() {
		String className = JOptionPane.showInputDialog("Please specify the name of the Java Class");
		try {
			Class classObj = Class.forName(className);
			ClassPaintComponent comp = new ClassPaintComponent(classObj, panel.getWidth() / 2, panel.getHeight() / 2);
			panel.addPaintComponent(comp);
			// add action to undo redo manager
			SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {

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
			return comp;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, className + " :: Class Not Found");
			return null;
		}
	}
}
