package actions.global.globalactions;

import paintcomponents.PaintComponent;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

/**
 * the global action of editting annotation size 
 * @author muchi
 *
 */
public class EditAnnotationSizeGlobalAction extends GlobalPaintAction {

	private Float input;
	private PaintComponent instance;
	
	/**
	 * @param instance the instance to set
	 */
	public void setInstance(PaintComponent instance) {
		this.instance = instance;
	}

	/**
	 * edit the size of the annotation
	 * 
	 * @param panel the panel
	 */
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		instance.getOptionalAnnotation().setFontSize(input);
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Float input) {
		this.input = input;
	}

}
