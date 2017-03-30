package actions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import actions.singleinstanceoperations.SingleInstanceOperation;
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
	protected void performActionOnInstance(PaintComponent instance) {
		// TODO Auto-generated method stub
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		new TextAnnotation(instance, annotations);
		
	}

	@Override
	protected Class<PaintComponent> getGenericClassType() {
		return PaintComponent.class;
	}


}
