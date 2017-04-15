package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.annotations.TextAnnotation;
import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;

public class RemoveAnnotationGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		AddAnnotationGlobalAction addAnnotationGlobalAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();

		DataDisplayPaintComponent add = new DataDisplayPaintComponent("", 0, 0);
		addAnnotationGlobalAction.setAnnotationToAdd("hello");
		addAnnotationGlobalAction.setOperatingInstance(add);
		addAnnotationGlobalAction.execute(panel);
		
		panel.addPaintComponent(add);
		
		RemoveAnnotationGlobalAction associatedAction = (RemoveAnnotationGlobalAction) ActionName.REMOVE_ANNOTATION_ACTION
				.getAssiciatedAction();
		associatedAction.setInstance(add);
						
		associatedAction.execute(panel);
				
		assertEquals(((TextAnnotation)add.getOptionalAnnotation()), null);
	}

}
