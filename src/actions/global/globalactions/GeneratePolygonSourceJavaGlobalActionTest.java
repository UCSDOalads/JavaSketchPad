package actions.global.globalactions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import paintcomponents.SimplePoint;
import ui.PaintPanel;

/**
 * GeneratePolygonSourceJavaGlobalAction Tester.
 * 
 * We test GeneratePolygonSourceJavaGlobalAction by comparing the actual out put
 * with an expected output.
 * 
 * @author Tan Su
 */
public class GeneratePolygonSourceJavaGlobalActionTest {

	@Test
	public void test() {
		PaintPanel panel = new PaintPanel();
		GeneratePolygonSourceJavaGlobalAction action = new GeneratePolygonSourceJavaGlobalAction();
		
		action.setWidth(108);
		action.setHeight(72);
		action.setName("GeneratedPolygon");
		
		ArrayList<SimplePoint> selectedComponents = new ArrayList<>();
		selectedComponents.add(new SimplePoint(100, 100));
		selectedComponents.add(new SimplePoint(200, 100));
		selectedComponents.add(new SimplePoint(200, 200));
		selectedComponents.add(new SimplePoint(100, 200));
		
		String expectedOutput = 
				    "import java.awt.Polygon;\n"
				  + "public class GeneratedPolygon{\n"
				  + "\n"
				  + "\n"
				  + 	"\tpublic static Polygon getPolygon(){\n"
				  + 	"\n"
				  + 		"\t\tPolygon p = new Polygon();\n"
				  + 		"\n"
				  + 		"\t\tp.addPoint(0, 0);\n"
				  + 		"\t\tp.addPoint(108, 0);\n"
				  + 		"\t\tp.addPoint(108, 72);\n"
				  + 		"\t\tp.addPoint(0, 72);\n"
				  + 		"\n"
				  + 		"\n"
				  + 		"\t\treturn p;\n"
				  + 		"\t}\n"
				  + 		"\n"
				  + 		"}";
		
		action.setSelectedComponents(selectedComponents);
		action.execute(panel);
		
		System.out.println(action.getResult());
		assertTrue(expectedOutput.compareTo(action.getResult()) == 0);
	}

}
