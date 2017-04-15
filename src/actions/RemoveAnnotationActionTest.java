package actions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.SimplePoint;
import paintcomponents.annotations.TextAnnotation;
import painttools.tools.SelectTool;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.globalactions.AddAnnotationGlobalAction;

public class RemoveAnnotationActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		SelectTool tool = new SelectTool(panel);
		panel.setSelectTool(tool);
		SimplePoint test = new SimplePoint (0, 0);
		panel.addPaintComponent(test);

		AddAnnotationGlobalAction addAnnotationGlobalAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();

		addAnnotationGlobalAction.setAnnotationToAdd("hello");
		addAnnotationGlobalAction.setOperatingInstance(test);
				
		addAnnotationGlobalAction.execute(panel);
		test.select(tool);
		
		System.out.println(tool.getSelectedComponents().get(0));
		
		RemoveAnnotationAction action = new RemoveAnnotationAction(panel);
		
		action.performAction();
		
		assertEquals(((TextAnnotation)test.getOptionalAnnotation()), null);
	}

}
