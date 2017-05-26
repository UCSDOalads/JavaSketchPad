package painttools.tools;

import java.awt.event.ActionListener;


/**
 * This is a interface for Action buttons in tool bar
 */
public interface ActionToolsInterface extends PaintToolsInterface, ActionListener {

	/**
	 * If you implements add paint actions, 
	 * 	1. make sure override the actionPeformed method, to define the actions when
	 * 	   button is clicked. 
	 * 
	 */
}
