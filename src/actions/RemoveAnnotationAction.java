package actions;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import ui.PaintPanel;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.RemoveAnnotationGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import actions.singleinstanceoperations.SingleInstanceOperation;

/**
 * remove the annotation of the component
 * 
 * @author muchi
 *
 */
public class RemoveAnnotationAction extends SingleInstanceOperation<PaintComponent>{

	/**
	 * ctor
	 * @param panel the panel of the component
	 */
	public RemoveAnnotationAction(PaintPanel panel) {
		super(panel);
	}

	/**
	 * the perform only can be performed when only one component is selected and the component has an annotation
	 * 
	 * @return true if the action can be performed, otherwise false
	 */
	@Override
	public boolean canPerformAction() {
		if(super.canPerformAction() == false){ return false;}
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		// check if the component has an annotation
		if(items.get(0).getOptionalAnnotation() == null){
			return false;
		}
		return true;
	}

	/**
	 * @return the location of the button
	 */
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Annotations().Remove().toString();
	}

	@Override
	protected void performActionOnInstance(PaintComponent instance) {
		// prepare the associated action
		RemoveAnnotationGlobalAction associatedAction = (RemoveAnnotationGlobalAction) ActionName.REMOVE_ANNOTATION_ACTION
				.getAssiciatedAction();
		
		// perform the action
		associatedAction.setInstance(instance);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);

		//push action to manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				String annotation = instance.getText();
				new TextAnnotation(instance, annotation);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				instance.setOptionalAnnotation(null);
				panel.repaint();
			}

			@Override
			protected String commandName() {
				return "remove an annotation";
			}

			@Override
			protected String commandDescription() {
				return "remove an annotation";
			}
		});
		panel.repaint();
	}

	@Override
	protected Class<PaintComponent> getGenericClassType() {
		// get the class of the paint component
		return PaintComponent.class;
	}

}
