package paintcomponents.java.lazy;

import java.lang.reflect.Field;
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
import typesystem.JavaType;
import ui.PaintPanel;

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
		addToPoint(1, new JavaType(displayingClass));
		
		for (int i = 0; i < fields.length; i++) {
			//the type is the type of the field
			addFromPoint(this, i + 2, new JavaType(fields[i].getType()));
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
@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		
		//build structure
		Element main = doc.createElement("fieldspaintcomponent");
		Element classNameElem = doc.createElement("classname");
		
		rootElement.appendChild(main);
		main.appendChild(classNameElem);
		
		classNameElem.appendChild(doc.createTextNode(displayingClass.getName()));
	}

	public FieldsPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement.getElementsByTagName("fieldspaintcomponent").item(0);
		Element classNameElem = (Element) main.getElementsByTagName("classname").item(0);
		
		try {
			this.displayingClass = Class.forName(classNameElem.getTextContent());
			this.setDisplayingText(this.displayingClass.getName());
			//make sure we set correct type for outgoint edges
			
			init();
			linkPoints(rootElement);
			

		} catch (ClassNotFoundException | DOMException e) {
			JOptionPane.showMessageDialog(panel, e.toString());
			e.printStackTrace();
		}
	}
}
