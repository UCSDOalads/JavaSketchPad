package ui.helper.historyui.undoredoLog;



import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

import ui.helper.historyui.HistoryDataObject;

public class UndoredoUITest {

	UndoredoLog undoredoLog;
	
	
	@Before
	public void setUp(){
		String[] arr = {"redo","undo"};
		
		undoredoLog.insert(new HistoryDataObject("AAAA"));
		undoredoLog.insert(new HistoryDataObject("BBB"));
		undoredoLog.insert(new HistoryDataObject("Test 1"));
		undoredoLog.insert(new HistoryDataObject("This is a item"));
		undoredoLog.insert(new HistoryDataObject("turn right"));
		undoredoLog.insert(new HistoryDataObject("112345"));
		
	}
	
	@Test
	public void test() throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//ClassSearchFrame classSearchFrame = new ClassSearchFrame();
				
				
				
				undoredoLog.setVisible(true);
				undoredoLog.setSize(new Dimension(500, 400));
				
				
			}
		});
		Thread.sleep(Long.MAX_VALUE);
	}

	
	
}
