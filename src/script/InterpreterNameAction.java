package script;

//import actions.singleinstanceoperations.AddAnnotationAction;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.NameGlobalAction;

public class InterpreterNameAction {

	public InterpreterNameAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		String token = tokenizer.next();

		NameGlobalAction assiciatedAction = (NameGlobalAction) ActionName.NAME
				.getAssiciatedAction();
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
		assiciatedAction.setName(token);

		// AddAnnotationAction annoAct = new AddAnnotationAction(panel);

	}
}
