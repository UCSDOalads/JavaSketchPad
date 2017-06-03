package ui.helper.historyui;



import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

public class TableUITest {

	TableUITemplate historyUI;
	
	
	@Before
	public void setUp(){
		String[] arr = {"delete","exit","clear","revert","confirm"};
		historyUI = new TableUITemplate(arr);
		historyUI.insert(new TableUIDataObject("AAAA"));
		historyUI.insert(new TableUIDataObject("BBB"));
		historyUI.insert(new TableUIDataObject("Test 1"));
		historyUI.insert(new TableUIDataObject("This is a item"));
		historyUI.insert(new TableUIDataObject("turn right"));
		historyUI.insert(new TableUIDataObject("112345"));
		
	}
	
	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//ClassSearchFrame classSearchFrame = new ClassSearchFrame();
				
				historyUI.setDelegate(new TableUITemplateInterface() {
					
					@Override
					public void didPressButton(String buttonName, int selectedRow) {
						System.out.println("buttonname = " + buttonName + "; selectedRow = " + selectedRow);
						
					}

					@Override
					public void addButtons() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void updateButtonStatus() {
						// TODO Auto-generated method stub
						
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
