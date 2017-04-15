package actions.global;

import ui.PaintPanel;

/**
 * Global paint Action corresponds to any event happening on the paint panel.
 * 
 * @author chenzb
 *
 */
public abstract class GlobalPaintAction<T extends GlobalPaintAction<?>> {

	protected abstract void execute(PaintPanel panel);


}
