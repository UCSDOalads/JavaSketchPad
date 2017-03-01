package paintcomponents.java.lazy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;
import paintcomponents.data.DataToPoint;

public class MethodPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider {

	private Method displayingMethod;

	public MethodPaintComponent(Method displayingMethod, int x, int y) {
		super(displayingMethod.toString(), x, y);
		this.displayingMethod = displayingMethod;
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
		addToPoint(1);
		// parameters take place from line 2 to length+1
		Class[] paramTypes = displayingMethod.getParameterTypes();
		for (int i = 2; i < paramTypes.length + 2; i++) {
			addToPoint(i);
		}

		// method's return value take line length+2
		addFromPoint(this, paramTypes.length + 2);

		// prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingMethod.toString() + "\n");
		s.append("<< Operating Instance  :: " + this.displayingMethod.toString()
				+ "\n");
		for (int i = 0; i < paramTypes.length; i++) {
			s.append("arg" + i + " :: " + paramTypes[i].getName() + "\n");
		}

		s.append("Return value"
				+ this.displayingMethod.getReturnType().toString() + " >>>> "
				+ "\n");
		setDisplayingText(s.toString());

	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		// TODO Auto-generated method stub

		// prepare argument list
		ArrayList<DataToPoint> toPoints = getToPoints();

		Object operatingInstance = null;
		try {
			operatingInstance = toPoints.get(0).fetchData();
		} catch (NoSuchElementException | NoConnectingLineSegmentException
				| DataFromPointNoDataProviderException
				| DataFromPointProviderCannotProvideDataException e1) {
			e1.printStackTrace();
			// TODO Handle Exception
			// Note: a static method may not contain an valid instance
			// throw new IllegalStateException();
		}

		// args takes toPoint 1 to size
		Object[] args = new Object[toPoints.size() - 1];

		for (int i = 1; i < toPoints.size(); i++) {
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
			return this.displayingMethod.invoke(operatingInstance, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException();
		}

	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		boolean isFirst = true;

		// this class is LAZY, don't care about states
		// skip the first toPoint if this method is static
		// TODO check if the method is static
		for (DataToPoint toPoint : getToPoints()) {
			if (isFirst) {
				isFirst = false;
				continue;
			}
			try {
				toPoint.fetchData();
			} catch (Exception e) {
				return false;
			}
			isFirst = false;

		}
		return true;
	}

}
