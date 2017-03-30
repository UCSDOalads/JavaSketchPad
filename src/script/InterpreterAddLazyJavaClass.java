package script;

import javax.swing.JOptionPane;

import actions.AddLazyJavaClassAction;
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
		PaintComponent comp = performAddJavaClassAction();
		if (tokenizer.hasNext()) {
			ComponentMap.map.put(tokenizer.next(), comp);
		}
	}

	private PaintComponent performAddJavaClassAction() {
		
		AddLazyJavaClassAction action = new AddLazyJavaClassAction(panel);
		action.performAction();
		return panel.getPaintComponents().get(panel.getPaintComponents().size() -1);
	}
}
