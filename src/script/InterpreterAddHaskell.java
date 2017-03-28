package script;

import javax.swing.JOptionPane;

import paintcomponents.haskell.EvaluateHaskellPaintComponent;
import paintcomponents.haskell.HaskellExpressionPaintComponent;
import ui.PaintPanel;

/**
 * Interpret and execute 'add_haskell' scripts 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddHaskell {

	private static final String EVALUATOR_COMPONENT = "evaluatorComponent";
	private static final String COMPONENT = "component";
	private PaintPanel panel;

	public InterpreterAddHaskell(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case COMPONENT:
				performAddComponentAction();
				break;

			case EVALUATOR_COMPONENT:
				performAddEvaluatorComponentAction();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performAddComponentAction() {
		String expr = JOptionPane
				.showInputDialog("Please enter the haskell expression");
		panel.addPaintComponent(new HaskellExpressionPaintComponent(expr, panel
				.getWidth() / 2, panel.getHeight() / 2));

		panel.repaint();
	}

	private void performAddEvaluatorComponentAction() {
		EvaluateHaskellPaintComponent comp = new EvaluateHaskellPaintComponent(
				"Use Data Display/Update to compute expression result",
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);
		panel.repaint();
	}

}
