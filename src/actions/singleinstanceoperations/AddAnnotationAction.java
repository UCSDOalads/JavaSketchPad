package actions.singleinstanceoperations;

import javax.swing.JOptionPane;

import paintcomponents.PaintComponent;
import ui.PaintPanel;
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
	protected Class<PaintComponent> getGenericClassType() {
		return PaintComponent.class;
	}


	@Override
	protected void performActionOnInstance(PaintComponent instance) {
		// ask for user input
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		
		// perform the action
		AddAnnotationGlobalAction associatedAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();
		associatedAction.setAnnotationToAdd(annotations);
		associatedAction.setOperatingInstance(instance);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
	}


}
