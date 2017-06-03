package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.annotations.TextAnnotation;
import paintcomponents.data.DataDisplayPaintComponent;
import actions.AddDataInputBoxAction;
import actions.global.ActionName;
import ui.PaintPanel;

public class AddAnnotationGlobalActionTest {

	@Test
	public void testExecute() {
		PaintPanel panel = new PaintPanel();
		
		AddAnnotationGlobalAction addAnnotationGlobalAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssociatedAction();

		DataDisplayPaintComponent test = new DataDisplayPaintComponent("", 0, 0);
		addAnnotationGlobalAction.setAnnotationToAdd("hello");
		addAnnotationGlobalAction.setOperatingInstance(test);
		
		panel.addPaintComponent(test);
		
		addAnnotationGlobalAction.execute(panel);
		
		assertEquals(((TextAnnotation)test.getOptionalAnnotation()).getStringContent(), "hello");
		
	}

}
