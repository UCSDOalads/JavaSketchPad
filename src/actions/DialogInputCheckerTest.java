package actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DialogInputCheckerTest {

	DialogInputChecker dialogInputChcker;
	
	@Before
	public void setUp(){
		dialogInputChcker = new DialogInputChecker();
	}
	@Test
	public void testIsEmpty() {
		//true cases
		assertTrue(dialogInputChcker.isEmpty(""));
		assertTrue(dialogInputChcker.isEmpty(null));
		
		//false cases
		assertTrue(!dialogInputChcker.isEmpty("1"));
		assertTrue(!dialogInputChcker.isEmpty("sa"));
	}
	
	@Test
	public void testIsValidNumber(){
		//true cases
		assertTrue(dialogInputChcker.isValidNumber("1"));
		assertTrue(dialogInputChcker.isValidNumber("10"));
		assertTrue(dialogInputChcker.isValidNumber("0"));
		assertTrue(dialogInputChcker.isValidNumber("-10"));
		assertTrue(dialogInputChcker.isValidNumber("1000"));
		
		
		//false cases
		assertTrue(!dialogInputChcker.isValidNumber("a"));
		assertTrue(!dialogInputChcker.isValidNumber(""));
		assertTrue(!dialogInputChcker.isValidNumber(null));
		assertTrue(!dialogInputChcker.isValidNumber("abc"));
		
	}
	
	@Test
	public void testIsValidNumberRange(){
		
		//true cases
		assertTrue(dialogInputChcker.isValidNumber("1",1,100));
		assertTrue(dialogInputChcker.isValidNumber("10",0,10));
		assertTrue(dialogInputChcker.isValidNumber("1",-1,100));
		assertTrue(dialogInputChcker.isValidNumber("-1",-1,1));
		
		
		//fasel cases
		assertTrue(!dialogInputChcker.isValidNumber("",0,100));
		assertTrue(!dialogInputChcker.isValidNumber("a",0,100));
		assertTrue(!dialogInputChcker.isValidNumber("-1",0,100));
		assertTrue(!dialogInputChcker.isValidNumber("0",1,100));

		
	}

}
