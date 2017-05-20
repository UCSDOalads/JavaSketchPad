package ui.helper.methodsearch;

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
				
				/* Comment below to test the framework */
				/* Added for the purpose of continuous integration */
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(5000);
							classSearchFrame.setVisible(false);
							classSearchFrame.dispose();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}).start();
				
			}
		});
		SwingUtilities.invokeLater(t);
		Thread.sleep(10000);


	}

}
