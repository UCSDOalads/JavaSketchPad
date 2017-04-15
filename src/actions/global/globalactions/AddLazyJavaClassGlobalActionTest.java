package actions.global.globalactions;

import static org.junit.Assert.*;

import org.junit.Test;

import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import actions.AddLazyJavaConstructorAction;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import java.util.ArrayList;

public class AddLazyJavaClassGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		AddLazyJavaClassGlobalAction assiciatedAction 
		= (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
				.getAssiciatedAction();
		assiciatedAction.setClassToCreate("string".getClass());

		assertEquals(0, panel.getPaintComponents().size());
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);
		assertEquals(1, panel.getPaintComponents().size());
		assertTrue(panel.getPaintComponents().get(0) instanceof ClassPaintComponent);
	}
}
