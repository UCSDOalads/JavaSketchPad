package paintcomponents.java.lazy;

import javax.swing.JOptionPane;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import typesystem.JavaType;
import ui.PaintPanel;

public class ClassPaintComponent extends DataInputTextfieldPaintComponent{
	
	Class displayingClass;

	/**
	 * @return the displayingClass
	 */
	public Class getDisplayingClass() {
		return displayingClass;
	}

	public ClassPaintComponent(Class displayingClass, int x, int y) {
		super("class " + displayingClass.getName(), x, y);
		this.displayingClass = displayingClass;
		//make sure we set correct type for outgoint edges
		this.getFromPoints().get(0).setExpectedType(new JavaType(displayingClass.getClass()));
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return displayingClass != null;
	}
	
	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return this.displayingClass;
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		
		//build structure
		Element main = doc.createElement("classpaintcomponent");
		Element classNameElem = doc.createElement("classname");
		
		rootElement.appendChild(main);
		main.appendChild(classNameElem);
		
		classNameElem.appendChild(doc.createTextNode(displayingClass.getName()));
	}

	public ClassPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement.getElementsByTagName("classpaintcomponent").item(0);
		Element classNameElem = (Element) main.getElementsByTagName("classname").item(0);
		
		try {
			this.displayingClass = Class.forName(classNameElem.getTextContent());
			this.setDisplayingText(this.displayingClass.getName());
			//make sure we set correct type for outgoint edges
			this.getFromPoints().get(0).setExpectedType(new JavaType(displayingClass.getClass()));
			
			//no linking since Data Text filed already linked

		} catch (ClassNotFoundException | DOMException e) {
			JOptionPane.showMessageDialog(panel, e.toString());
			e.printStackTrace();
		}
	}
	
	
}
