package paintcomponents.java.lazy;

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

public class ClassConstructorPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider {

	private Constructor displayingConstructor;

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

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		// TODO Auto-generated method stub

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
			return displayingConstructor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			// TODO Handle Exception
			// TODO Handle Exception
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {

		// this class is LAZY, don't care about states
		for (DataToPoint toPoint : getToPoints()) {
			try {
				toPoint.fetchData();
			} catch (Exception e) {
				return false;
			}

		}
		return true;
	}

	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		// build the structure
		Element main = doc.createElement("classconstructorcomponent");
		Element className = doc.createElement("classname");
		Element constructorInfoElem = doc
				.createElement("constructorinfo");

		main.appendChild(className);
		main.appendChild(constructorInfoElem);
		rootElement.appendChild(main);

		// store the class name in the classname element
		className.setTextContent(displayingConstructor.getDeclaringClass().getName());

		// this approach connot deal with arrays and primitives
		// //store a list of constructor types in constructorParamType element
		// Class[] parameterTypes = displayingConstructor.getParameterTypes();
		// for (Class type : parameterTypes) {
		// Element typeElem = doc.createElement("typename");
		// typeElem.appendChild(doc.createTextNode(type.getName()));
		// constructorParamType.appendChild(typeElem);
		// }

		/* Index approach */
		constructorInfoElem
				.setAttribute("index",
						Integer.toString(Arrays.asList(this.displayingConstructor
								.getDeclaringClass().getConstructors())
								.indexOf(displayingConstructor)));

	}

	public ClassConstructorPaintComponent(Element rootElement,
			PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement
				.getElementsByTagName("classconstructorcomponent").item(0);
		Element classNameElem = (Element) main
				.getElementsByTagName("classname").item(0);
		Element constructorInfoElem = (Element) main
				.getElementsByTagName("constructorinfo").item(0);



		String className = classNameElem.getTextContent();
		
		//index appproach
		try {
			Class consClass = Class.forName(className);
			this.displayingConstructor = consClass.getConstructors()[Integer.parseInt(constructorInfoElem.getAttribute("index"))];
			this.setDisplayingText(displayingConstructor.toString());
			init();
			linkPoints(rootElement);

		} catch (ClassNotFoundException | DOMException | SecurityException e) {
			JOptionPane.showMessageDialog(panel, e.toString());
			e.printStackTrace();
		}
		
		
		/* type approach */
//		NodeList types = constructorParamTypesElem
//				.getElementsByTagName("typename");
//		Class[] paramTypes = new Class[types.getLength()];
//
//		for (int i = 0; i < types.getLength(); i++) {
//			Element typeElem = (Element) types.item(i);
//			String typeName = typeElem.getTextContent();
//			try {
//				paramTypes[i] = Class.forName(typeName);
//			} catch (ClassNotFoundException e) {
//				JOptionPane.showMessageDialog(panel, e.toString());
//				e.printStackTrace();
//			}
//		}
//
//		try {
//			Class consClass = Class.forName(className);
//			this.displayingConstructor = consClass.getConstructor(paramTypes);
//			this.setDisplayingText(displayingConstructor.toString());
//			init();
//			linkPoints(rootElement);
//
//		} catch (ClassNotFoundException | DOMException | NoSuchMethodException
//				| SecurityException e) {
//			JOptionPane.showMessageDialog(panel, e.toString());
//			e.printStackTrace();
//		}

	}
	

}
