package actions.global.globalactions;

import javax.swing.JOptionPane;

import actions.global.GlobalPaintAction;
import paintcomponents.haskell.HaskellExpressionPaintComponent;
import ui.PaintPanel;

public class AddHaskellComponentGlobalAction extends GlobalPaintAction {

	private Class classToCreate;

	/**
	 * @param classToCreate
	 *            the classToCreate to set
	 */
	public void setClassToCreate(Class classToCreate) {
		this.classToCreate = classToCreate;
	}

	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub

		String expr = JOptionPane.showInputDialog("Please enter the haskell expression");
		panel.addPaintComponent(new HaskellExpressionPaintComponent(expr, panel.getWidth() / 2, panel.getHeight() / 2));

		panel.repaint();
	}

}
