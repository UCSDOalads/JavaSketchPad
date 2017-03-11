package ui.helper;

import static org.junit.Assert.*;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class ClassSearchFrameTest {

	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ClassSearchFrame().setVisible(true);
				
			}
		});
		Thread.sleep(Long.MAX_VALUE);
	}

}
