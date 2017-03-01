package paintcomponents.data;

import java.awt.Graphics;

import painttools.tools.SelectTool;

/**
 * A data text with a point on the right
 * 
 * @author chenzb
 *
 */
public class DataInputTextfieldPaintComponent extends DataTextIOPaintComponent
		implements DataFromPointDataProvider{


	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		addFromPoint(this, 0);
	}

	public void inputData(String s) {
		this.setDisplayingText(s);
	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return displayingText;
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return displayingText != null;
	}

	
}
