package paintcomponents.java.lazy;

import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import paintcomponents.data.DataTextIOPaintComponent;

public class ClassPaintComponent extends DataInputTextfieldPaintComponent{
	
	Class displayingClass;

	/**
	 * @return the displayingClass
	 */
	public Class getDisplayingClass() {
		return displayingClass;
	}

	public ClassPaintComponent(Class displayingClass, int x, int y) {
		super(displayingClass.getName(), x, y);
		this.displayingClass = displayingClass;
		//make sure we set correct type for outgoint edges
		this.getFromPoints().get(0).setExpectedType(displayingClass.getClass().getName());
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
	
}
