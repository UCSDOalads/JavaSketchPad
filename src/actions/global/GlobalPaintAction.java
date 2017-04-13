package actions.global;

import ui.PaintPanel;

/**
 * Global paint Action corresponds to any event happening on the paint panel. 
 * Every menu itmes, paint tools send to action manager an instance of this action.  
 * @author chenzb
 *
 */
public abstract class GlobalPaintAction<T extends GlobalPaintAction<?>> {

	protected abstract void execute(PaintPanel panel);

}
