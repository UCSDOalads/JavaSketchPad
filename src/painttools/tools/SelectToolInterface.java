package painttools.tools;

import java.util.ArrayList;

import paintcomponents.PaintComponent;

public interface SelectToolInterface {

	/**
	 * @return the selectedComponents
	 */
	public abstract ArrayList<PaintComponent> getSelectedComponents();

	/**
	 * Selects a component, changes selection All listeners are informed. Panel
	 * are repainted
	 * 
	 * @param comp
	 */
	public abstract void selectComponent(PaintComponent comp);

	/**
	 * Deselect a component, changes selection All listeners are informed. Panel
	 * are repainted
	 * 
	 * @param comp
	 */
	public abstract void deselectComponent(PaintComponent comp);

	/**
	 * Deselect ALL components, changes selection All listeners are informed.
	 * Panel are repainted
	 */
	public abstract void clearSelection();

	public abstract boolean addSelectionToolListener(SelectionToolListener e);

}