package paintcomponents;

import java.awt.Graphics;

import painttools.tools.SelectTool;

/**
 * A data text with a point on the right
 * 
 * @author chenzb
 *
 */
public class DataInputTextfieldPaintComponent extends DataTextPaintComponent
		implements DataFromPointDataProvider<String>{

	private static final int HORIZONTAL_OFFSET = 10;
	private DataFromPoint<String> fromPoint;

	public DataInputTextfieldPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		this.fromPoint = new DataFromPoint<>(x, y);
		fromPoint.setProvider(this);
	}

	@Override
	protected void paintSelected(Graphics g) {
		super.paintSelected(g);
		updateFromPointPosition();
		fromPoint.paint(g);
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		super.paintNotSelected(g);
		updateFromPointPosition();
		fromPoint.paint(g);
	}

	/**
	 * This method will use the protected bounds, which will be updated in
	 * super.paint[Not]Selected. Make sure you've already invoked super's
	 * paintNotSelectedMethod before invoking this one.
	 */
	private void updateFromPointPosition() {
		this.fromPoint.setX(
				(int) (getX() + this.bounds.getWidth() + HORIZONTAL_OFFSET));
		this.fromPoint.setY((int) (getY() + this.bounds.getHeight() / 2));
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.fromPoint.translate(i, j);
	}

	@Override
	public boolean contains(int x, int y) {

		return fromPoint.contains(x, y) || super.contains(x, y);
	}

	public void inputData(String s) {
		this.setDisplayingText(s);
	}

	@Override
	public String provideInformationToDataFromPoint(
			DataFromPoint<String> dataFromPoint) {
		return displayingText;
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint<String> dataFromPoint) {
		return displayingText == null;
	}

	@Override
	public void select(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		if (fromPoint.contains(x, y)) {
			fromPoint.select(selectTool);
		} else {
			super.select(selectTool);
		}

	}

	@Override
	public void deselect(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		if (fromPoint.contains(x, y)) {
			fromPoint.deselect(selectTool);
		} else {
			super.deselect(selectTool);
		}
	}
	
	@Override
	public boolean isSelected() {
		//if the from point is selected, this components is considered selected
		if(this.fromPoint.isSelected()) return true;
		return super.isSelected();
	}

	

	
}
