package ui.helper.historyui;



import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

public class HistoryUITest {

	HistoryUI historyUI;
	
	
	@Before
	public void setUp(){
		String[] arr = {"cacel","revert","confirm"};
		historyUI = new HistoryUI(arr);
		historyUI.insert(new HistoryDataObject("AAAA"));
		historyUI.insert(new HistoryDataObject("BBB"));
	}
	
	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//ClassSearchFrame classSearchFrame = new ClassSearchFrame();
				
				historyUI.setDelegate(new HistoryUIInterface() {
					
					@Override
					public void didPressButton(String buttonName, int selectedRow) {
						System.out.println("buttonname = " + buttonName + "; selectedRow = " + selectedRow);
						
					}
				});
				
				
				historyUI.setVisible(true);
				historyUI.setSize(new Dimension(300, 200));
				
				
				/* Comment below to test the framework */
				/* Added for the purpose of continuous integration */
				try {
					Thread.sleep(5000);
					historyUI.dispose();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		Thread.sleep(10000);
	}

	
	
}
