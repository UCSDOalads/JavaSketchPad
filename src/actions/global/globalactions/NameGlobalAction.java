package actions.global.globalactions;

import paintcomponents.PaintComponent;
import script.ComponentMap;
import ui.PaintPanel;
import actions.edit.undoredo.UndoRedoableInterface;

public class NameGlobalAction extends SingleInstanceOperationGlobalAction {

	private PaintComponent comp;
	private String token = "";

	@Override
	public void execute(PaintPanel panel) {
		comp = panel.getPaintComponents().get(
				panel.getPaintComponents().size() - 1);

		// add action to undo redo manager
		UndoRedoableInterface undoRedoBlock = new UndoRedoableInterface() {

			@Override
			public void undoAction() {
				ComponentMap.map.remove(token);
			}

			@Override
			public void redoAction() {
				ComponentMap.map.put(token, comp);
			}

			@Override
			protected String commandName() {
				return "name";
			}

			@Override
			protected String commandDescription() {
				return "name a component";
			}
		};
	}

	/**
	 * @param annotationToAdd
	 *            the annotationToAdd to set
	 */
	public void setName(String token) {
		this.token = token;
		ComponentMap.map.put(token, comp);
	}

}