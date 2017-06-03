package script;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import ui.PaintPanel;
import actions.RemovePaintComponent;

/**
 * Interpret and execute 'remove' script
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterRemoveAction {

	private PaintPanel panel;
	private ArrayList<PaintComponent> comps = new ArrayList<>();

	public InterpreterRemoveAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		// check if name of a component is specified
		if (tokenizer.hasNext()) {
			panel.getSelectTool().clearSelection();
			new InterpreterSelectAction(tokenizer, panel);
		}

		for (PaintComponent comp : panel.getSelectTool()
				.getSelectedComponents()) {
			comps.add(comp);
		}

		this.panel = panel;
		performRemoveSelected();
	}

	private void performRemoveSelected() {
		for (PaintComponent comp : comps) {
			if (ComponentMap.map.containsValue(comp)) {
				ComponentMap.map.values().remove(comp);
			}
		}

		RemovePaintComponent remAct = new RemovePaintComponent(panel);
		remAct.performAction();
	}
}
