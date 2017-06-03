package paintcomponents.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import typesystem.JavaType;
import ui.PaintPanel;

/**
 * A data text with a point on the right
 * 
 * @author chenzb
 *
 */
public class DataInputTextfieldPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider{


	//Protect the class box from being updated
	boolean canUpdate = true;
	
	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		addFromPoint(this, 0, new JavaType(String.class));
	}

	public void inputData(String s) {
		this.setDisplayingText(s);
	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return getDisplayingText();
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return getDisplayingText() != null;
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//save does nothing as this particular class does not have any instance properties 
	}

	public DataInputTextfieldPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		//do the same thing as the designated constructor
		addFromPoint(this, 0, new JavaType(String.class));
		//link the points
		linkPoints(rootElement);
	}

	/*
	 * Set this input box to be "in-updatable"
	 * Now this should only be used by the class input box
	 */
	public void setCannotUpdate(){
		this.canUpdate = false;
	}
	
	/*
	 * Getter for whether it's updatable.
	 */
	public boolean canUpdate(){
		return this.canUpdate;
	}

}
