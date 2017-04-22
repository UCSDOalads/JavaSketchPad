/*
 * This test file tests the functionalities of global file save
 * and file open by first: create some paint components, second:
 * save it to a file, third: clear the panel and open the file,
 * last: check whether the components have been restored.
 */
package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

/*
 * @author Yidong Luo
 */
public class FileOpenGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		
		//Add string class to the panel
		AddLazyJavaClassGlobalAction assiciatedAction 
		 	= (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
						.getAssiciatedAction();
		assiciatedAction.setClassToCreate("string".getClass());
		
		assertEquals(0, panel.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof ClassPaintComponent);
		
		//Save them
		FileSaveAsGlobalAction fileSaveAssiciatedAction 
	 	= (FileSaveAsGlobalAction) ActionName.FILE_SAVE_AS_GLOBAL_ACTION
					.getAssiciatedAction();
		fileSaveAssiciatedAction.setFilePath("temp.xml");
		fileSaveAssiciatedAction.execute(panel);
		
		
		//Remove all the current components
		panel = new PaintPanel();
		panel.updateUI();
		
		//Nothing should be on the panel now
		assertEquals(0, panel.getPaintComponents().size());

		//Try to open them again
		FileOpenGlobalAction fileOpenAssiciatedAction 
	 	= (FileOpenGlobalAction) ActionName.FILE_OPEN_GLOBAL_ACTION
					.getAssiciatedAction();
		fileOpenAssiciatedAction.setFileToOpen("temp.xml");
		fileOpenAssiciatedAction.execute(panel);
		
		//Components on the panel should be the same as what was saved to be
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) 
				instanceof ClassPaintComponent);
		
			
	}

}