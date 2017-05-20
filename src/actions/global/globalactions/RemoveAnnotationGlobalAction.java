package actions.global.globalactions;

import paintcomponents.PaintComponent;
import actions.global.GlobalPaintAction;
import ui.PaintPanel;

public class RemoveAnnotationGlobalAction extends GlobalPaintAction {
	
	PaintComponent instance;
	
	public void setInstance(PaintComponent instance) {
		this.instance = instance;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		instance.setOptionalAnnotation(null);
	}

}
