package actions.global.globalactions;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;

public class AddLazyJavaConstructorGlobalActionTest {

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
		AddLazyJavaConstructorGlobalAction assiciatedAction = (AddLazyJavaConstructorGlobalAction) ActionName.ADD_LAZY_JAVA_CONSTRUCTOR_ACTION
				.getAssiciatedAction();
		assiciatedAction.setComponent(comp);

		// test
		assertEquals(0, panel.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof ClassConstructorPaintComponent);
	}

}
