package script;

import ui.PaintPanel;

public class InterpreterSelectAction {
	
	public InterpreterSelectAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		String token = tokenizer.next();
		if (ComponentMap.map.containsKey(token)) {
			ComponentMap.map.get(token).select(panel.getSelectTool());
		} else {
			throw new ExecutionErrorException("component not found");
		}
	}
}
