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
import java.io.File;
import java.nio.file.Files;

/*
 * @author Yidong Luo
 */
public class FileOpenAndSaveGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		
		//Add string class to the panel
		AddLazyJavaClassGlobalAction associatedAction 
		 	= (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
						.getAssociatedAction();
		associatedAction.setClassToCreate("string".getClass());
		
		assertEquals(0, panel.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof ClassPaintComponent);
		
		//Save them
		FileSaveAsGlobalAction fileSaveAssociatedAction 
	 	= (FileSaveAsGlobalAction) ActionName.FILE_SAVE_AS_GLOBAL_ACTION
					.getAssociatedAction();

		fileSaveAssociatedAction.setFilePath("JSPFileSaveOpenTest.xml");

		fileSaveAssociatedAction.execute(panel);
		
		
		//Remove all the current components
		panel = new PaintPanel();
		panel.updateUI();
		
		//Nothing should be on the panel now
		assertEquals(0, panel.getPaintComponents().size());

		//Try to open them again
		FileOpenGlobalAction fileOpenAssociatedAction 
	 	= (FileOpenGlobalAction) ActionName.FILE_OPEN_GLOBAL_ACTION
					.getAssociatedAction();

		fileOpenAssociatedAction.setFileToOpen("JSPFileSaveOpenTest.xml");

		fileOpenAssociatedAction.execute(panel);
		
		//Components on the panel should be the same as what was saved to be
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) 
				instanceof ClassPaintComponent);
		
		File xml = new File("JSPFileSaveOpenTest.xml");
		
		xml.delete();
		

	}

}