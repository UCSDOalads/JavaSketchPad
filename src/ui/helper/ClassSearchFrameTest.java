package ui.helper;

import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class ClassSearchFrameTest {

	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ClassSearchFrame classSearchFrame = new ClassSearchFrame();
				classSearchFrame.setDelegate(new ClassSearchFrameDelegateInterface() {
					
					@Override
					public void didSelectClass(String classname) {
						System.out.println(classname);
					}
				});
				
				
				classSearchFrame.setVisible(true);
				classSearchFrame.setSize(new Dimension(300, 200));
				
				
			}
		});
		Thread.sleep(Long.MAX_VALUE);
	}

}
