package paintcomponents.java.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;
import paintcomponents.data.DataToPoint;

public class ClassConstructorPaintComponent extends DataTextIOPaintComponent implements DataFromPointDataProvider  {

	private Constructor displayingConstructor;

	public ClassConstructorPaintComponent(Constructor displayingContructor, int x, int y) {
		super(displayingContructor.toString(), x, y);
		this.displayingConstructor = displayingContructor;
		init();
	}

	private void init() {
		
		
		//parameters take place from line 1 to length
		Class[] paramTypes = displayingConstructor.getParameterTypes();
		for(int i = 0; i < paramTypes.length; i ++){
			addToPoint(i + 1);
		}
		
		//constructed instance take line length+1
		addFromPoint(this, paramTypes.length + 1);

		
		//prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingConstructor.toString() + "\n");
		for(int i = 0; i < paramTypes.length; i ++){
			s.append("arg" + i + " :: " + paramTypes[i].getName() + "\n");
		}
		
		s.append("Constructed Instance >>>> " + "\n");
		setDisplayingText(s.toString());
		
		
	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		// TODO Auto-generated method stub
		
		//prepare argument list
		ArrayList<DataToPoint> toPoints = getToPoints();
		Object[] args = new Object[toPoints.size()];
		for(int i = 0; i < toPoints.size(); i ++){
			DataToPoint toPoint = toPoints.get(i);
			try {
				args[i] = toPoint.fetchData();
			} catch (NoSuchElementException | NoConnectingLineSegmentException
					| DataFromPointNoDataProviderException
					| DataFromPointProviderCannotProvideDataException e) {
				e.printStackTrace();
				//TODO Handle Exception
				throw new IllegalStateException();
			}
		}
		try {
			return displayingConstructor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
				//TODO Handle Exception
				//TODO Handle Exception
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		
		//this class is LAZY, don't care about states
		for (DataToPoint toPoint: getToPoints()) {
			try{
				toPoint.fetchData();
			} catch( Exception e){
				return false;
			}
			
		}
		return true;
	}

	
	
}
