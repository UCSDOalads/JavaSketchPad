package actions.singleinstanceoperations;

import java.util.ArrayList;

import javax.swing.JOptionPane;

<<<<<<< HEAD:src/actions/AddAnnotationAction.java
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
=======
import actions.global.ActionName;
import actions.global.GlobalPaintAction;
import actions.global.globalactions.SingleInstanceOperationGlobalAction;
>>>>>>> 02b1d78f9f2b7b350607b5352e27bc2c0d066cee:src/actions/singleinstanceoperations/AddAnnotationAction.java
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
<<<<<<< HEAD:src/actions/AddAnnotationAction.java
	protected void performActionOnInstance(PaintComponent instance) {
		// TODO Auto-generated method stub
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		new TextAnnotation(instance, annotations);
		instance.setText(annotations);
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
		
=======
	protected Class<PaintComponent> getGenericClassType() {
		return PaintComponent.class;
>>>>>>> 02b1d78f9f2b7b350607b5352e27bc2c0d066cee:src/actions/singleinstanceoperations/AddAnnotationAction.java
	}

	@Override
	protected ActionName getExecutingAction() {
		return ActionName.ADD_ANNOTATION_ACTION;
	}


}
