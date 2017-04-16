package paintcomponents.java.interactive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;
import paintcomponents.data.DataToPoint;
import typesystem.JavaType;
import ui.PaintPanel;

/*
 * This is the interactive method paint component, must call the evaluate 
 * method to calculate the data inside before trying to get data from this 
 * component.
 */
public class MethodPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider {

	private Method displayingMethod;
	//The data stored
	private Object returnVal;
	//Store an instance of the object this method manipulates
	private Object instance;

	public MethodPaintComponent(Method displayingMethod, Object instance, int x, int y) {
		super(displayingMethod.toString(), x, y);
		this.displayingMethod = displayingMethod;
		this.instance = instance;
		init();
	}
	
	/*
	 * 
	 * line 0 is signature
	 * 
	 * parameters take place from line 2 to length+1
	 * 
	 * method's return value take line length+2
	 * 
	 */

	private void init() {

		// parameters take place from line 2 to length+1
		Class[] paramTypes = displayingMethod.getParameterTypes();
		for (int i = 0; i < paramTypes.length ; i++) {
			addToPoint(i + 1, new JavaType(paramTypes[i]));
		}

		// method's return value take line length+1 since we no longer have a from point for instance
		addFromPoint(this, paramTypes.length + 1, new JavaType(displayingMethod.getReturnType()));
		
		// prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingMethod.toString() + "\n");
		for (int i = 0; i < paramTypes.length; i++) {
			s.append("arg" + i + " :: " + paramTypes[i].getName() + "\n");
		}

		s.append("Return value :: "
				+ this.displayingMethod.getReturnType().toString() + " >>>> "
				+ "\n");
		setDisplayingText(s.toString());

	}

	/**
	 * Calculate the input data and store it.
	 */
	public void evaluate(){
		
		// prepare argument list
		ArrayList<DataToPoint> toPoints = getToPoints();

		Object operatingInstance = instance;
		

		// args takes toPoint 1 to size
		Object[] args = new Object[toPoints.size()];

		//Get the input data from each the input points
		for (int i = 0; i < toPoints.size(); i++) {
			DataToPoint toPoint = toPoints.get(i);
			try {
				args[i] = toPoint.fetchData();
			} catch (NoSuchElementException | NoConnectingLineSegmentException
					| DataFromPointNoDataProviderException
					| DataFromPointProviderCannotProvideDataException e) {
				e.printStackTrace();
				// TODO Handle Exception
				// arguments must be valid
				throw new IllegalStateException();
			}
		}

		try {
			returnVal = this.displayingMethod.invoke(operatingInstance, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException();
		}

	}
	
	/**
	 * Retrieve the data stored in this component
	 * @param dataFromPoint
	 * @return the data stored
	 */
	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {	
			return returnVal;	
	}
	
	/**
	 * Check whether the data is good to return from this component
	 * @param dataFromPoint
	 * @return   the return value
	 */
	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		
		return returnVal != null;
	}
	
	/**
	 * Getter for instance
	 * @return  instance ref
	 */
	public Object getInstance(){
		return instance;
	}
	
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	
	public Object executeMethod() {
		try {
			if (instance == null) {
				JOptionPane.showMessageDialog(null, "Please initiate an instance");
				return null;
			}
			Object[] initargs = new Object[this.getToPoints().size()];
			for (int i = 0; i < initargs.length; i ++) {
				DataFromPoint fromP = 
						this.getToPoints().get(i).getLineSegment().getFromPoint();
				initargs[i] = fromP.getProvider().provideInformationToDataFromPoint(fromP);
			}
			Object returnVal = this.displayingMethod.invoke(instance, initargs);			
			System.out.println("method return val: " + returnVal);
			return returnVal;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Occurs");
			e.printStackTrace();
		}
		return null;
	}

	
}
