package actions.global.globalactions;

import paintcomponents.PaintComponent;
import ui.PaintPanel;
import actions.global.GlobalPaintAction;

/**
 * the global action of editting annotation size 
 * @author muchi
 *
 */
public class EditAnnotationSizeGlobalAction extends GlobalPaintAction {

	private Float textSize;
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
		instance.getOptionalAnnotation().setFontSize(textSize);
	}

	/**
	 * @param input the input to set
	 */
	public void setTextSize(Float newTextSize) {
		this.textSize = newTextSize;
	}

}
