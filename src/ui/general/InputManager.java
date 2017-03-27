package ui.general;

import javax.swing.JOptionPane;

import ui.PaintPanel;

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
	
	// TODO askForClass
	public void askForClass(PaintPanel panel, InputManagerDelegate<Integer> delegate) {
		String input = JOptionPane.showInputDialog("Please Input A Class");
		try{
			//TODO Class
			//delegate.didFinishInput(inputInt);
		} catch (NumberFormatException exp){
			exp.printStackTrace();
			askForClass(panel, delegate);
		}
	}

}
