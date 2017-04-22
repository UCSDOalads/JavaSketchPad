package actions;

import java.util.ArrayList;

import actions.menu.ActionsMenuBarTitles;
import actions.singleinstanceoperations.SingleInstanceOperation;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

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

	
	protected void performActionOnInstance(PaintComponent instance) {
		// TODO Auto-generated method stub
		instance.setOptionalAnnotation(null);
	}

	@Override
	protected Class<PaintComponent> getGenericClassType() {
		// TODO Auto-generated method stub
		return PaintComponent.class;
	}



}
