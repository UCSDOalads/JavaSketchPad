package actions.global.globalactions;

import javax.swing.JOptionPane;

import paintcomponents.PaintComponent;
import paintcomponents.annotations.TextAnnotation;
import ui.PaintPanel;

public class AddAnnotationGlobalAction extends SingleInstanceOperationGlobalAction<AddAnnotationGlobalAction> {


	@Override
	public void execute(PaintPanel panel, PaintComponent instance) {
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		new TextAnnotation(instance, annotations);
	}

}
