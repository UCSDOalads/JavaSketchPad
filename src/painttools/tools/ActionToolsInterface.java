package painttools.tools;

import java.awt.event.ActionListener;


/**
 * This is a interface for Action buttons in tool bar
 */
public interface ActionToolsInterface extends PaintToolsInterface, ActionListener {

	/**
	 *  If you implement add paint action tools:
	 *  
	 * 	1. make sure override the actionPeformed method to define actions when
	 * 	   button is clicked. 
	 * 
	 * 	2. modify mouseClicked method to defined actions to perform when click
	 * 	   on paintPanel
	 * 
	 *  3. you can call setX and setY to set the starting point for paintComponent
	 *  
	 *  4. you may need to call createButton in constructor to create a button for
	 *     this tool, and you can set icons for button in createButton.
	 * 
	 *  
	 */
}
