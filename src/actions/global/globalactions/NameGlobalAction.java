package actions.global.globalactions;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import script.ComponentMap;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

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
				comp.setOptionalAnnotation(null);
				panel.repaint();
			}

			@Override
			public void redoAction() {
				ComponentMap.map.put(token, comp);
				new TextAnnotation(comp, token);
				panel.repaint();
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
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				undoRedoBlock);

	}

	/**
	 * @param annotationToAdd
	 *            the annotationToAdd to set
	 */
	public void setName(String token, PaintPanel panel) {
		this.token = token;
		ComponentMap.map.put(token, comp);

		// Add annotation
		AddAnnotationGlobalAction associatedAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();
		associatedAction.setAnnotationToAdd(token);
		associatedAction.setOperatingInstance(comp);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction,
				panel);
		new TextAnnotation(comp, token);
		panel.repaint();
	}

}