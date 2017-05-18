package script;

import ui.PaintPanel;
import actions.AddTextBoxAction;

/**
 * Interpret and execute 'add' scripts
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddActions {

	private static final String TEXT_BOX = "textBox";
	private static final String LAZY = "lazy";
	private static final String HASKELL = "haskell";
	private static final String DATA = "data";
	private PaintPanel panel;

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
				performAddTextBox();
				break;

			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performAddTextBox() {
		AddTextBoxAction action = new AddTextBoxAction(panel);
		action.performAction();
	}
}
