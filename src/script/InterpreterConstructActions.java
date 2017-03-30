package script;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import paintcomponents.LineSegment;
import paintcomponents.PaintComponent;
import paintcomponents.SimplePoint;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataLineSegment;
import paintcomponents.data.DataToPoint;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Interpret and execute 'construct' scripts 
 * @author Xiaoquan Jiang
 */
public class InterpreterConstructActions {

	private static final String LINE_SEGMENT = "lineSegment";
	private static final String DATA_LINE_SEGMENT = "dataLineSegment";
	private PaintPanel panel;

	public InterpreterConstructActions(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case DATA_LINE_SEGMENT:
				performAddDataLineSegment();
				break;

			case LINE_SEGMENT:
				performAddLineSegment();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performAddDataLineSegment() {
		ArrayList<PaintComponent> comps = this.panel.getSelectTool()
				.getSelectedComponents();
		DataFromPoint fromPoint = (DataFromPoint) comps.get(0);
		DataToPoint toPoint = (DataToPoint) comps.get(1);
		if (!fromPoint.getExpectedType().equals(toPoint.getExpectedType())) {
			int result = JOptionPane.showConfirmDialog(panel, "The source type is "
					+ fromPoint.getExpectedType() + ", the destination type is "
					+ toPoint.getExpectedType()
					+ ". Do you want to proceed and create the connection anyway?",

			"Type Mismatch", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.NO_OPTION) {
				return;
			}

		}
		DataLineSegment seg = new DataLineSegment(fromPoint, toPoint);
		addLineSegment(seg);
		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

					@Override
					public void undoAction() {
						seg.remove(panel);
						panel.repaint();
					}

					@Override
					public void redoAction() {
						panel.addPaintComponent(seg);
						panel.repaint();
					}
				});
	}

	private void performAddLineSegment() {
		// get selected components
		ArrayList<PaintComponent> items = panel.getSelectTool()
				.getSelectedComponents();

		// construct line segment
		LineSegment lineSegment = new LineSegment((SimplePoint) (items.get(0)),
				(SimplePoint) (items.get(1)));

		addLineSegment(lineSegment);
	}

	/**
	 * This method updates the panel's list of paint components and selection
	 * after a line segment is added Subclasses should call this method to update
	 * the panel when customizing the addition of a line segment
	 * 
	 * @param lineSegment
	 *          the lineSegment to be added to the painting panel
	 */
	private void addLineSegment(LineSegment lineSegment) {

		// add to panel
		panel.addPaintComponent(lineSegment);

		// change selection
		panel.getSelectTool().clearSelection();
		panel.getSelectTool().selectComponent(lineSegment);
		
		panel.repaint();
	}
}
