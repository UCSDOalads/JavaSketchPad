package actions.singleinstanceoperations;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.global.ActionName;
import actions.global.GlobalPaintAction;
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
	protected ActionName getExecutingAction() {
		return ActionName.ADD_ANNOTATION_ACTION;
	}


}
