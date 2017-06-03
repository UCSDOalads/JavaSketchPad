package actions.global.globalactions;

import javax.swing.JOptionPane;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import ui.PaintPanel;

public class AddAnnotationGlobalAction extends SingleInstanceOperationGlobalAction {

	private String annotationToAdd;
	@Override
	public void execute(PaintPanel panel){
		new TextAnnotation(getOperatingInstance(), annotationToAdd);
	}
	/**
	 * @return the annotationToAdd
	 */
	public String getAnnotationToAdd() {
		return annotationToAdd;
	}
	
	/**
	 * @param annotationToAdd the annotationToAdd to set
	 */
	public void setAnnotationToAdd(String annotationToAdd) {
		this.annotationToAdd = annotationToAdd;
	}

	
}
