package actions;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.EditAnnotationSizeGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import actions.singleinstanceoperations.SingleInstanceOperation;

/**
 * edit the size of the component
 * 
 * @author muchi
 *
 */
public class EditAnnotationSizeAction extends SingleInstanceOperation<PaintComponent>{

	/**
	 * ctor
	 * @param panel the panel of the component
	 */
	public EditAnnotationSizeAction(PaintPanel panel) {
		super(panel);
	}

	/**
	 * the action can only be performed when there is only one component selected and the component has a annotation
	 * 
	 * @return true if action can be performed, otherwise false
	 */
	@Override
	public boolean canPerformAction(){
		if(super.canPerformAction() == false){ return false;}
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		// check if the annotation of the component is not null
		if(items.get(0).getOptionalAnnotation() == null){
			return false;
		}
		
		return true;	
	}
	
	@Override
	protected void performActionOnInstance(PaintComponent instance) {
		InputManager.sharedInstance().askForFloat(panel, new InputManagerDelegate<Float>() {
			
			@Override
			public void didFinishInput(Float input) {
						EditAnnotationSizeGlobalAction associatedAction = (EditAnnotationSizeGlobalAction) ActionName.EDIT_ANNOTATION_SIZE_ACTION
								.getAssiciatedAction();
						associatedAction.setTextSize(input);
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
		});
		
	}

	@Override
	protected Class<PaintComponent> getGenericClassType() {
		return PaintComponent.class;
	}

	/**
	 * @return the location of the button
	 */
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Annotation_Font_Size().toString();
	}

}
