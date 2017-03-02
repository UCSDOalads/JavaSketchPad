package paintcomponents.java.lazy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;

public class FieldsPaintComponent extends DataTextIOPaintComponent implements DataFromPointDataProvider{
	
	private Class displayingClass;

	public FieldsPaintComponent(Class displayingClass, int x, int y) {
		super(displayingClass.getName(), x, y);
		this.displayingClass = displayingClass;
		
		init();
	}

	private void init() {
		/*
		 * Line 1 class name
		 * line 2 in instance
		 * line 3 .. the fields
		 */
		Field[] fields = displayingClass.getFields();
		
		//the left receiving instance
		addToPoint(1, displayingClass.getName());
		
		for (int i = 0; i < fields.length; i++) {
			//the type is the type of the field
			addFromPoint(this, i + 2, fields[i].getType().getName());
		}
		
		
		// prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingClass.toString() + "\n");
		s.append(">>> Operating Instance  " 
				+ "\n");
		for (int i = 0; i < fields.length; i++) {
			s.append("field" + i + " :: " + fields[i].getName() + " :: " + fields[i].getType().toString() + "\n");
		}

		setDisplayingText(s.toString());

	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		int index = getFromPoints().indexOf(dataFromPoint);
		Field[] fields = displayingClass.getFields();
		Field field = fields[index];
		
		Object operatingInstance = null;
		try {
			operatingInstance = getToPoints().get(0).fetchData();
		} catch (NoSuchElementException | NoConnectingLineSegmentException
				| DataFromPointNoDataProviderException
				| DataFromPointProviderCannotProvideDataException e1) {
			e1.printStackTrace();
			// TODO Handle Exception
			// Note: a static method may not contain an valid instance
			// throw new IllegalStateException();
		}
		
		try {
			return field.get(operatingInstance);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO Handle Exception
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		//TODO IMPORTANT Implement this method
		return true;
	}

}
