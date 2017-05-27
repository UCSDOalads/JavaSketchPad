package actions.singleinstanceoperations;

import javax.swing.JOptionPane;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddAnnotationGlobalAction;
import actions.menu.ActionsMenuBarTitles;

/**
 * add the annotation to a component
 * 
 * @author muchi
 *
 */
public class AddAnnotationAction extends SingleInstanceOperation<PaintComponent>{

	/**
	 * ctor
	 * @param panel the panel
	 */
	public AddAnnotationAction(PaintPanel panel) {
		super(panel);
	}

	/**
	 * @return the location of the button
	 */
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Annotations().Add().toString();
	}

	@Override
	protected void performActionOnInstance(PaintComponent instance) {
		// TODO Auto-generated method stub
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		// perform the action
		AddAnnotationGlobalAction associatedAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();
		associatedAction.setAnnotationToAdd(annotations);
		associatedAction.setOperatingInstance(instance);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction,
				panel);
		//push action to manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				instance.setOptionalAnnotation(null);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				new TextAnnotation(instance, annotations);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "add an annotation";
			}

			@Override
			protected String commandDescription() {
				return "add an annotation";
			}

			
		});
		panel.repaint();
	}
		

	protected Class<PaintComponent> getGenericClassType() {
		return PaintComponent.class;

	}

	/*
	 * @Override protected void performActionOnInstance(PaintComponent instance)
	 * { // ask for user input String annotations = JOptionPane
	 * .showInputDialog("Please specify the annotation of the component");
	 * 
	 * 
	 * }
	 */

}
