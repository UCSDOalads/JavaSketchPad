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

public class MethodPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider {

	private Method displayingMethod;
	private Object returnVal;
	private Object instance;

	public MethodPaintComponent(Method displayingMethod, Object instance, int x, int y) {
		super(displayingMethod.toString(), x, y);
		this.displayingMethod = displayingMethod;
		this.instance = instance;
		init();
	}
	
	/*
	 * 
	 * 
	 * line 0 is signature
	 * 
	 * 
	 * 
	 * line 1 is the operating instance
	 * 
	 * 
	 * 
	 * parameters take place from line 2 to length+1
	 * 
	 * 
	 * 
	 * method's return value take line length+2
	 * 
	 * 
	 * 
	 * 
	 */

	private void init() {

		// line 0 is signature
		// line 1 is the operating instance
		addToPoint(1, new JavaType(this.displayingMethod.getDeclaringClass()));
		// parameters take place from line 2 to length+1
		Class[] paramTypes = displayingMethod.getParameterTypes();
		for (int i = 0; i < paramTypes.length ; i++) {
			addToPoint(i + 2, new JavaType(paramTypes[i]));
		}

		// method's return value take line length+2
		//**** Update: The from point should be on length+1 now since the instance line is removed ****
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
	public void evaluate(DataFromPoint dataFromPoint){
		
		// prepare argument list
		ArrayList<DataToPoint> toPoints = getToPoints();

		Object operatingInstance = instance;
		

		// args takes toPoint 1 to size
		Object[] args = new Object[toPoints.size() - 1];

		for (int i = 0; i < toPoints.size() - 1; i++) {
			DataToPoint toPoint = toPoints.get(i+1);
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
	 * Retrieve the instance of what this component manipulated.
	 * @param dataFromPoint  
	 * @return  that instance
	 */
	public Object provideInstanceToDataFromPoint(
			DataFromPoint dataFromPoint){
		return instance;
	}
	
	/**
	 * Check whether there's an instance to retrieve
	 * @param dataFromPoint
	 * @return true if there is, false if not
	 */
	public boolean canProvideInstanceToDataFromPoint(
			DataFromPoint dataFromPoint){
		return instance != null;
	}
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		// build the structure
		Element main = doc.createElement("methodcomponent");
		Element className = doc.createElement("classname");
		Element methodInfoElem = doc
				.createElement("methodinfo");

		main.appendChild(className);
		main.appendChild(methodInfoElem);
		rootElement.appendChild(main);

		// store the class name in the classname element
		className.setTextContent(displayingMethod.getDeclaringClass().getName());

		/* Index approach */
		methodInfoElem
				.setAttribute("index",
						Integer.toString(Arrays.asList(this.displayingMethod
								.getDeclaringClass().getMethods())
								.indexOf(displayingMethod)));

	}

	public  MethodPaintComponent(Element rootElement,
			PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement
				.getElementsByTagName("methodcomponent").item(0);
		Element classNameElem = (Element) main
				.getElementsByTagName("classname").item(0);
		Element methodInfoElem = (Element) main
				.getElementsByTagName("methodinfo").item(0);



		String className = classNameElem.getTextContent();
		
		//index appproach
		try {
			Class mtdClass = Class.forName(className);
			this.displayingMethod = mtdClass.getMethods()[Integer.parseInt(methodInfoElem.getAttribute("index"))];
			this.setDisplayingText(displayingMethod.toString());
			init();
			linkPoints(rootElement);

		} catch (ClassNotFoundException | DOMException | SecurityException e) {
			JOptionPane.showMessageDialog(panel, e.toString());
			e.printStackTrace();
		}
		
		
		

	}
}
