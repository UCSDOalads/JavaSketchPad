package actions.singleinstanceoperations;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.global.ActionName;
import actions.global.GlobalPaintAction;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddAnnotationGlobalAction;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.SingleInstanceOperationGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import paintcomponents.data.DataTextPaintComponent;
import ui.PaintPanel;

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
		// TODO Auto-generated method stub
		/*String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		new TextAnnotation(instance, annotations);
		AddLazyJavaClassGlobalAction assiciatedAction 
		= (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
		assiciatedAction.setClassToCreate(input);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);*/
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		
		AddAnnotationGlobalAction associatedAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();
		associatedAction.setAnnotationToAdd(annotations);
		associatedAction.setOperatingInstance(instance);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
	}


}
