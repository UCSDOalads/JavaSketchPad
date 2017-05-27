package script;

import ui.PaintPanel;
import actions.AddDataDisplayBoxAction;
import actions.AddDataInputBoxAction;

/**
 * Interpret and execute 'add data' scripts
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddData {
	private static final String INPUT_BOX = "inputBox";
	private static final String DISPLAY_BOX = "displayBox";
	private PaintPanel panel;

	public InterpreterAddData(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case DISPLAY_BOX:
				performAddDisplayBoxAction();
				break;

			case INPUT_BOX:
				performAddInputBoxAction();
				break;

			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performAddDisplayBoxAction() {
		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);
		action.performAction();
	}

	private void performAddInputBoxAction() {
		AddDataInputBoxAction action = new AddDataInputBoxAction(panel);
		action.performAction();
	}
}
