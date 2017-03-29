package paintcomponents.java.interactive;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
 * This is the interactive constructor paint component, must call evaluate
 * to update the instance this component manipulates before use the output.
 */
public class ClassConstructorPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider {

	private Constructor displayingConstructor;
	//Store a reference to the object this component operates.
	private Object instance;


	public ClassConstructorPaintComponent(Constructor displayingContructor,
			int x, int y) {
		super(displayingContructor.toString(), x, y);
		this.displayingConstructor = displayingContructor;
		init();
	}

	private void init() {

		// parameters take place from line 1 to length
		Class[] paramTypes = displayingConstructor.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			addToPoint(i + 1, new JavaType(paramTypes[i]));
		}

		// constructed instance take line length+1
		addFromPoint(this, paramTypes.length + 1,
				new JavaType(this.displayingConstructor.getDeclaringClass()));
		
		// prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingConstructor.toString() + "\n");
		for (int i = 0; i < paramTypes.length; i++) {
			s.append("arg" + i + " :: " + paramTypes[i].getName() + "\n");
		}

		s.append("Constructed Instance >>>> " + "\n");
		setDisplayingText(s.toString());

	}

	/**
	 * Calculate the input data and store it.
	 */
	public void evaluate(DataFromPoint dataFromPoint){
		// prepare argument list
				ArrayList<DataToPoint> toPoints = getToPoints();
				Object[] args = new Object[toPoints.size()];
				for (int i = 0; i < toPoints.size(); i++) {
					DataToPoint toPoint = toPoints.get(i);
					try {
						args[i] = toPoint.fetchData();
					} catch (NoSuchElementException | NoConnectingLineSegmentException
							| DataFromPointNoDataProviderException
							| DataFromPointProviderCannotProvideDataException e) {
						e.printStackTrace();
						// TODO Handle Exception
						throw new IllegalStateException();
					}
				}
				try {
					instance = displayingConstructor.newInstance(args);
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					// TODO Handle Exception
					throw new IllegalStateException();
				}
	}
	
	/**
	 * Retrieve the data stored in this component
	 * @return the data stored
	 */
	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {

		return instance;
	}

	/**
	 * Check whether the data is good to return from this component
	 * @param dataFromPoint
	 * @return   the return value
	 */
	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {

		return instance != null;
	}

}
