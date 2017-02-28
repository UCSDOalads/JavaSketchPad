package paintcomponents;

import java.awt.Graphics;
import java.util.NoSuchElementException;

import painttools.tools.SelectTool;

/**
 * The data display paint component displays the data with a asoociated DataToPoint
 * @author chenzb
 *
 */
//TODO THIS class is a copy of DataInputTextfieldPaintComponent class, please consider abstraction
public class DataDisplayPaintComponent extends DataTextPaintComponent {

	private static final int HORIZONTAL_OFFSET = 10;
	private DataToPoint<String> toPoint;

	public DataDisplayPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		this.toPoint = new DataToPoint<>(x, y);
	}

	@Override
	protected void paintSelected(Graphics g) {
		super.paintSelected(g);
		updateFromPointPosition();
		toPoint.paint(g);
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		super.paintNotSelected(g);
		updateFromPointPosition();
		toPoint.paint(g);
	}

	/**
	 * This method will use the protected bounds, which will be updated in
	 * super.paint[Not]Selected. Make sure you've already invoked super's
	 * paintNotSelectedMethod before invoking this one.
	 */
	private void updateFromPointPosition() {
		this.toPoint.setX(
				(int) (getX() - HORIZONTAL_OFFSET));
		this.toPoint.setY((int) (getY() + this.bounds.getHeight() / 2));
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.toPoint.translate(i, j);
	}

	@Override
	public boolean contains(int x, int y) {

		return toPoint.contains(x, y) || super.contains(x, y);
	}

	public void inputData(String s) {
		this.setDisplayingText(s);
	}


	@Override
	public void select(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		if (toPoint.contains(x, y)) {
			toPoint.select(selectTool);
		} else {
			super.select(selectTool);
		}

	}

	@Override
	public void deselect(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		if (toPoint.contains(x, y)) {
			toPoint.deselect(selectTool);
		} else {
			super.deselect(selectTool);
		}
	}
	
	@Override
	public boolean isSelected() {
		//if the from point is selected, this components is considered selected
		if(this.toPoint.isSelected()) return true;
		return super.isSelected();
	}

	/**
	 * Update the current display.
	 * 
	 * This class will try to fetch the data from the toPointClass
	 * @throws DataFromPointProviderCannotProvideDataException 
	 * @throws DataFromPointNoDataProviderException 
	 * @throws NoConnectingLineSegmentException 
	 * @throws NoSuchElementException 
	 * @see DataToPoint.fetchData for exception details
	 */
	public void displayText() throws NoSuchElementException, NoConnectingLineSegmentException, DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException{
		this.setDisplayingText(toPoint.fetchData());
	}
	


}
