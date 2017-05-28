package ui.helper.historyui;

public interface TableUITemplateInterface {

	  /**
	   * Called when a button is pressed.
	   * @param buttonName The name of the button
	   * @param selectedRow the index of selected row. -1 if no row selected
	   */

	public void didPressButton(String buttonName, int selectedRow);
	
	
	/**
	 * implement addButtons method to add buttons to the button_panel
	 */
	public abstract void addButtons();
	
	/**
	 * implement updateButtonStatus to update button when button actions performanced
	 */
	public abstract void updateButtonStatus();
	
	
}
