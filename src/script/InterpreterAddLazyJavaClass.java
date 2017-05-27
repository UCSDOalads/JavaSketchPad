package script;

import ui.PaintPanel;
import actions.AddLazyJavaClassAction;

public class InterpreterAddLazyJavaClass {

	private PaintPanel panel;

	public InterpreterAddLazyJavaClass(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;
		performAddJavaClassAction();
	}

	private void performAddJavaClassAction() {
		AddLazyJavaClassAction action = new AddLazyJavaClassAction(panel);
		action.performAction();
	}
}
