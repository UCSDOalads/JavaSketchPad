package ui.helper.historyui;



import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

public class HistoryUITest {

	HistoryUI historyUI;
	
	
	@Before
	public void setUp(){
		String[] arr = {"delete","exit","clear","revert","confirm"};
		historyUI = new HistoryUI(arr);
		historyUI.insert(new HistoryDataObject("AAAA"));
		historyUI.insert(new HistoryDataObject("BBB"));
		historyUI.insert(new HistoryDataObject("Test 1"));
		historyUI.insert(new HistoryDataObject("This is a item"));
		historyUI.insert(new HistoryDataObject("turn right"));
		historyUI.insert(new HistoryDataObject("112345"));
		
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
				historyUI.setSize(new Dimension(500, 400));
				
				
				/* Comment below to test the framework */
				/* Added for the purpose of continuous integration */
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						try {
							Thread.sleep(50);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
				
				
				
			}
		});
		Thread.sleep(50);
	}

	
	
}
