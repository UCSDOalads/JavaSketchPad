package script;

import ui.PaintPanel;
import actions.AddLazyJavaConstructorAction;
import actions.AddLazyJavaFieldsComponentAction;
import actions.AddLazyJavaMethodComponentAction;

/**
 * Interpret and execute 'add_lazy' scripts
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddLazy {

	private static final String JAVA_METHOD_COMPONENT = "javaMethodComponent";
	private static final String JAVA_FIELDS_COMPONENT = "javaFieldsComponent";
	private static final String JAVA_CONSTRUCTOR = "javaConstructor";
	private static final String JAVA_CLASS = "javaClass";
	private PaintPanel panel;

	public InterpreterAddLazy(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		String token;
		String nextToken;
		this.panel = panel;

		if (tokenizer.hasNext()) {
			token = tokenizer.next();

			// check if the component to add is a java class
			if (token.equals(JAVA_CLASS)) {
				new InterpreterAddLazyJavaClass(tokenizer, panel);
			} else {
				if (tokenizer.hasNext()) {
					nextToken = tokenizer.next();
					// select class
					if (nextToken.equals("-c")) {
						panel.getSelectTool().clearSelection();
						new InterpreterSelectAction(tokenizer, panel);
					}
				}

				switch (token) {
				case JAVA_CONSTRUCTOR:
					performAddJavaConstructorAction();
					break;

				case JAVA_FIELDS_COMPONENT:
					performAddJavaFieldsComponentAction();
					break;

				case JAVA_METHOD_COMPONENT:
					performAddJavaMethodComponentAction();
					break;

				default:
					throw new ExecutionErrorException("invalid script");
				}
			}
		} else {
			throw new ExecutionErrorException("invalid script");
		}
	}

	private void performAddJavaConstructorAction() {
		AddLazyJavaConstructorAction consAct = new AddLazyJavaConstructorAction(
				panel);
		consAct.performAction();
	}

	private void performAddJavaFieldsComponentAction() {
		AddLazyJavaFieldsComponentAction filedAct = new AddLazyJavaFieldsComponentAction(
				panel);
		filedAct.performAction();
	}

	private void performAddJavaMethodComponentAction() {
		AddLazyJavaMethodComponentAction metAct = new AddLazyJavaMethodComponentAction(
				panel);
		metAct.performAction();
	}
}
