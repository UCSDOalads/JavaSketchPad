package actions.global.globalactions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import actions.ExecuteInstanceConstructorAction;
import painttools.tools.SelectTool;
import ui.PaintPanel;




public class ExecuteInstanceMethodGlobalActionTest2 {
	PaintPanel panel;
	AddInstanceMethodGlobalAction addInsMthAction;
	AddInstanceOperationGlobalAction addInstance;
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
		panel = new PaintPanel();


	}


	//test String length method
	@Test
	public void test() {
		//first add a instance operation component
		
		addInstance = new AddInstanceOperationGlobalAction();
		addInstance.setConstructorToSet(String.class.getConstructors()[9]);//String();
		addInstance.execute(panel);
		

		
		//set select tool
		panel.setSelectTool(new SelectTool(panel));
		
		
		//initial constructor
		ExecuteInstanceConstructorAction constructor = new ExecuteInstanceConstructorAction(panel);
		panel.getSelectTool().addSelectedComponent(panel.getPaintComponents().get(0));
		constructor.performAction();
		
		//add a method based on the operation component
		addInsMthAction = new AddInstanceMethodGlobalAction();
		addInsMthAction.setInsComp(addInstance.getInsComp());
		addInsMthAction.setMethodToSet(String.class.getMethods()[18]);//String.length();
		addInsMthAction.execute(panel);
		
		//execute method
		ExecuteInstanceMethodGlobalAction executeMethod = new ExecuteInstanceMethodGlobalAction();
		panel.getSelectTool().removeSelectedComponent(panel.getPaintComponents().get(0));
		panel.getSelectTool().addSelectedComponent(panel.getPaintComponents().get(1));
		assertEquals(0,executeMethod.doExecute(panel));
		
	}
	
	//test ArrayList isEmpty
	@Test
	public void test1(){
		//first add an ArrayList instance operation component
		addInstance = new AddInstanceOperationGlobalAction();
		addInstance.setConstructorToSet(ArrayList.class.getConstructors()[1]);//String();
		addInstance.execute(panel);
		
		//set select tool
		panel.setSelectTool(new SelectTool(panel));
		
		
		//initial constructor
		ExecuteInstanceConstructorAction constructor = new ExecuteInstanceConstructorAction(panel);
		panel.getSelectTool().addSelectedComponent(panel.getPaintComponents().get(0));
		constructor.performAction();
		
		//add ArrayList isEmpty method based on the operation component
		addInsMthAction = new AddInstanceMethodGlobalAction();
		addInsMthAction.setInsComp(addInstance.getInsComp());
		addInsMthAction.setMethodToSet(ArrayList.class.getMethods()[8]);//String.length();
		addInsMthAction.execute(panel);
		
		//execute method
		ExecuteInstanceMethodGlobalAction executeMethod = new ExecuteInstanceMethodGlobalAction();
		panel.getSelectTool().removeSelectedComponent(panel.getPaintComponents().get(0));
		panel.getSelectTool().addSelectedComponent(panel.getPaintComponents().get(1));
		assertEquals(true,executeMethod.doExecute(panel));
	}
}
