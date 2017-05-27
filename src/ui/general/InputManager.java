package ui.general;

import java.awt.Dimension;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import ui.PaintPanel;
import ui.helper.classsearch.ClassSearchFrame;
import ui.helper.classsearch.ClassSearchFrameDelegateInterface;
import ui.helper.methodinput.MethodInputFrame;
import ui.helper.methodinput.MethodSearchFrameDelegateInterface;

/**
 * Ask for things from user.
 * Delegate will be notified upon successful input.
 * Nothing happens if user dismisses request
 * @author chenzb
 *
 */
public class InputManager {
	
	private static InputManager sharedInstance = new InputManager();
	
	public static InputManager sharedInstance(){
		return sharedInstance;
		
	}
	
	
	/**
	 * Ask for a float from user
	 * @param panel
	 * @param delegate
	 */
	public void askForFloat(PaintPanel panel, InputManagerDelegate<Float> delegate){
		String input = JOptionPane.showInputDialog("Please Input A Float");
		try{
			float inputFloat = Float.parseFloat(input);
			delegate.didFinishInput(inputFloat);
		} catch (NumberFormatException exp){
			exp.printStackTrace();
			askForFloat(panel, delegate);
		}
	}
	/**
	 * Ask for a double from user
	 * @param panel
	 * @param delegate
	 */
	public void askForDouble(PaintPanel panel, InputManagerDelegate<Double> delegate){
		String input = JOptionPane.showInputDialog("Please Input A Double");
		try{
			double inputDouble = Double.parseDouble(input);
			delegate.didFinishInput(inputDouble);
		} catch (NumberFormatException exp){
			exp.printStackTrace();
			askForDouble(panel, delegate);
		}
	}

	/**
	 * Ask for an integer from user
	 * @param panel
	 * @param delegate
	 */
	public void askForInt(PaintPanel panel, InputManagerDelegate<Integer> delegate) {
		String input = JOptionPane.showInputDialog("Please Input A Integer");
		try{
			int inputInt = Integer.parseInt(input);
			delegate.didFinishInput(inputInt);
		} catch (NumberFormatException exp){
			exp.printStackTrace();
			askForInt(panel, delegate);
		}
	}
	
	public void askForClass(PaintPanel panel, InputManagerDelegate<Class> delegate) {
		ClassSearchFrame classSearchFrame = new ClassSearchFrame();
		classSearchFrame.setDelegate(new ClassSearchFrameDelegateInterface() {
				
			@Override
			public void didSelectClass(String classname) {
					
				try {
					Class classInput = Class.forName(classname);
					delegate.didFinishInput(classInput);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(panel,
							classname + " :: Class Not Found");
				}
				
			}
		});
			
			
		classSearchFrame.setVisible(true);
		classSearchFrame.setSize(new Dimension(300, 200));

	}
	
	
	public void askForMethod(PaintPanel panel, InputManagerDelegate<Method> delegate, Class c) {
		MethodInputFrame methodSearchFrame = new MethodInputFrame(c);
		
		methodSearchFrame.setTitle("Add a Method");
		
		methodSearchFrame.setDelegate(new MethodSearchFrameDelegateInterface() {
				
			@Override
			public void didSelectMethod(String methodName) {
				
				for (Method method : c.getMethods()) {
					if (methodName.equals(method.toString())) {
						delegate.didFinishInput(method);
						return;
					}
				}
				
				JOptionPane.showMessageDialog(panel, methodName + " :: Method Not Found");
				
			}
		});
			
			
		methodSearchFrame.setVisible(true);
		methodSearchFrame.setSize(new Dimension(600, 400));
		methodSearchFrame.setLocation(panel.getX() + panel.getWidth() / 2 - 300, 
				panel.getY() + panel.getHeight() / 2 - 200);

	}
	
	

}
