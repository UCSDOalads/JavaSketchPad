package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import paintcomponents.haskell.HaskellExpressionPaintComponent;
import ui.PaintPanel;

public class AddHaskellComponentGlobalAction extends GlobalPaintAction {


	private String haskellExpression;
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub

		String expr = haskellExpression;
		panel.addPaintComponent(new HaskellExpressionPaintComponent(expr, panel.getWidth() / 2, panel.getHeight() / 2));

		panel.repaint();
	}

	/**
	 * @param haskellExpression
	 *            the haskellExpression to set
	 */
	public void setHaskellExpression(String haskellExpression) {
		this.haskellExpression = haskellExpression;
	}

}
