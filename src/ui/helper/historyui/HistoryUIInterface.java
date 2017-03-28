package ui.helper.historyui;

public interface HistoryUIInterface {

  /**
   * Called when a button is pressed.
   * @param buttonName The name of the button
   * @param selectedRow the index of selected row. -1 if no row selected
   */

public void didPressButton(String buttonName, int selectedRow);
}
