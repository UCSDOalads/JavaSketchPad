package interation;

import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

public class ClassSearchFrameTest {

	historyUI classSearchFrame;
	
	
	@Before
	public void setUp(){
		String[] arr = {"cacel","revert","confirm"};
		classSearchFrame = new historyUI(arr);
		classSearchFrame.insert(new historyDataObject("AAAA"));
		classSearchFrame.insert(new historyDataObject("BBB"));
	}
	
	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//ClassSearchFrame classSearchFrame = new ClassSearchFrame();
				
				classSearchFrame.setDelegate(new ClassSearchFrameDelegateInterface() {
					
					@Override
					public void didPressButton(String buttonName, int selectedRow) {
						System.out.println("buttonname = " + buttonName + "; selectedRow = " + selectedRow);
						
					}
				});
				
				
				classSearchFrame.setVisible(true);
				classSearchFrame.setSize(new Dimension(300, 200));
				
				
			}
		});
		Thread.sleep(Long.MAX_VALUE);
	}

	
	
}
