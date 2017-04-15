package actions;

import java.util.ArrayList;

import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.EditAnnotationSizeGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import actions.singleinstanceoperations.SingleInstanceOperation;
import paintcomponents.annotations.*;

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
				associatedAction.setInput(input);
				associatedAction.setInstance(instance);
				GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
				panel.repaint();
			}
		});
		
	}

	@Override
	protected Class<PaintComponent> getGenericClassType() {
		// TODO Auto-generated method stub
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
