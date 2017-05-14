package script;

//import actions.singleinstanceoperations.AddAnnotationAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class InterpreterNameAction {

	public InterpreterNameAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		String token = tokenizer.next();
		PaintComponent comp = panel.getPaintComponents().get(
				panel.getPaintComponents().size() - 1);

		ComponentMap.map.put(token, comp);

		// AddAnnotationAction annoAct = new AddAnnotationAction(panel);

	}
}
