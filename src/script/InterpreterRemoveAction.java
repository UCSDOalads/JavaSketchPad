package script;

import java.util.ArrayList;
import paintcomponents.PaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Interpret and execute 'remove' script
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterRemoveAction {

	private PaintPanel panel;
	private ArrayList<PaintComponent> comps = new ArrayList<>();
	private String token;

	public InterpreterRemoveAction(Tokenizer tokenizer, PaintPanel panel) throws ExecutionErrorException {

		// check if name of a component is specified
		if (tokenizer.hasNext()) {
			token = tokenizer.next();
			if (ComponentMap.map.containsKey(token)) {
				comps.add(ComponentMap.map.get(token));
			} else {
				throw new ExecutionErrorException("component not found");

			}
		} else {
			for (PaintComponent comp : panel.getSelectTool().getSelectedComponents()) {
				comps.add(comp);
			}
		}

		this.panel = panel;
		performRemoveSelected();
	}

	private void performRemoveSelected() {
		for (PaintComponent comp : comps) {
			comp.remove(panel);
			if (ComponentMap.map.containsValue(comp)) {
				ComponentMap.map.values().remove(comp);
			}
		}

		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {

			@Override
			public void undoAction() {
				for (PaintComponent comp : comps)
					panel.addPaintComponent(comp);
			}

			@Override
			public void redoAction() {
				for (PaintComponent comp : comps)
					comp.remove(panel);
			}
		});
		panel.repaint();
	}
}
