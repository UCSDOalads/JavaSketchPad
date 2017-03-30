package script;

import javax.swing.JOptionPane;
import paintcomponents.TextPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;
import paintcomponents.*;

/**
 * Interpret and execute 'add' scripts 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddActions {

	private static final String TEXT_BOX = "textBox";
	private static final String LAZY = "lazy";
	private static final String HASKELL = "haskell";
	private static final String DATA = "data";
	private PaintPanel panel;
	private PaintComponent component;

	public InterpreterAddActions(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
	  this.panel = panel;
	  
		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case DATA:
				new InterpreterAddData(tokenizer, panel);
				break;

			case HASKELL:
				new InterpreterAddHaskell(tokenizer, panel);
				break;

			case LAZY:
				new InterpreterAddLazy(tokenizer, panel);
				break;

			case TEXT_BOX:
				component = performAddTextBox();
		    break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
		
		// name and store the component added
    if (tokenizer.hasNext() && component != null) {
      ComponentMap.map.put(tokenizer.next(), component);
    }
	}

	private PaintComponent performAddTextBox() {
		String s = JOptionPane.showInputDialog("Please enter the text to display");
		TextPaintComponent comp = new TextPaintComponent(s, panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(comp);
		// push action to the manager
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
		return comp;
	}
}
