package actions.global.globalactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

public class AddLazyJavaFieldsComponentGlobalActionTest {

    @Test
    public void test() {
	PaintPanel panel = new PaintPanel();

	// add a class
	AddLazyJavaClassGlobalAction classAction = (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
		.getAssiciatedAction();
	classAction.setClassToCreate("string".getClass());
	GlobalPaintActionExecuter.getSharedInstance().execute(classAction,
		panel);

	// set up
	ClassPaintComponent comp = (ClassPaintComponent) panel
		.getPaintComponents().get(0);
	AddLazyJavaFieldsComponentGlobalAction assiciatedAction = (AddLazyJavaFieldsComponentGlobalAction) ActionName.ADD_LAZY_JAVA_FIELDS_ACTION
		.getAssiciatedAction();
	assiciatedAction.setComponent(comp);

	// test
	assertEquals(0, panel.getPaintComponents().size());
	GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
		panel);
	assertEquals(1, panel.getPaintComponents().size());
	assertTrue(panel.getPaintComponents().get(0) instanceof FieldsPaintComponent);
    }

}
