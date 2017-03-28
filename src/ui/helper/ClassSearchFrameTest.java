package ui.helper;

import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class ClassSearchFrameTest {

	@Test
	public void test() throws InterruptedException {
		Runnable t = (new Runnable() {
			
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
				
				try {
					Thread.sleep(10000);
					classSearchFrame.setVisible(false);
					classSearchFrame.dispose();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		SwingUtilities.invokeLater(t);
		Thread.sleep(100000);


	}

}
