package actions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import paintcomponents.SimplePoint;
import paintcomponents.annotations.TextAnnotation;
import painttools.tools.SelectTool;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.globalactions.AddAnnotationGlobalAction;

/**
 * test the removeAnnotationAction by adding and then deleting the annotation.
 * Then check if the annotation is null
 * 
 * @author muchi
 */
public class RemoveAnnotationActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		SelectTool tool = new SelectTool(panel);
		panel.setSelectTool(tool);
		SimplePoint test = new SimplePoint (0, 0);
		panel.addPaintComponent(test);

		// add the annotation to be removed
		AddAnnotationGlobalAction addAnnotationGlobalAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();

		addAnnotationGlobalAction.setAnnotationToAdd("hello");
		addAnnotationGlobalAction.setOperatingInstance(test);
				
		addAnnotationGlobalAction.execute(panel);
		test.select(tool);
		
		// perform the remove action
		RemoveAnnotationAction action = new RemoveAnnotationAction(panel);
		
		action.performAction();
		
		assertEquals(((TextAnnotation)test.getOptionalAnnotation()), null);
	}

}
