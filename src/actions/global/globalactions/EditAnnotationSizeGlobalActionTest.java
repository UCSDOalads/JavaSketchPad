package actions.global.globalactions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import paintcomponents.annotations.TextAnnotation;
import paintcomponents.data.DataDisplayPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;

/**
 * Test editAnnotationSizeGlobalAction by adding annotations and then edit the
 * size. Then check if the size changes as desired
 * 
 * @author muchi
 *
 */
public class EditAnnotationSizeGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();

		AddAnnotationGlobalAction addAnnotationGlobalAction = (AddAnnotationGlobalAction) ActionName.ADD_ANNOTATION_ACTION
				.getAssiciatedAction();

		// add component and annotations
		DataDisplayPaintComponent add = new DataDisplayPaintComponent("", 0, 0);
		addAnnotationGlobalAction.setAnnotationToAdd("hello");
		addAnnotationGlobalAction.setOperatingInstance(add);
		addAnnotationGlobalAction.execute(panel);

		panel.addPaintComponent(add);

		// edit the size
		EditAnnotationSizeGlobalAction associatedAction = (EditAnnotationSizeGlobalAction) ActionName.EDIT_ANNOTATION_SIZE_ACTION
				.getAssiciatedAction();
		associatedAction.setTextSize((float) 40.0);
		associatedAction.setInstance(add);

		associatedAction.execute(panel);

		assertEquals(((TextAnnotation) add.getOptionalAnnotation())
				.getTextPaintComponent().getFontSize(), (float) 40.0, 0.1);
	}

}
