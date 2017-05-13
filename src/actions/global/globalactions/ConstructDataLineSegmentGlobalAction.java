package actions.global.globalactions;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataLineSegment;
import paintcomponents.data.DataToPoint;
import ui.PaintPanel;

/**
 * Global action to construct data line segment
 * 
 * @author Shanfeng Feng
 * @since 2017-04-22
 */
public class ConstructDataLineSegmentGlobalAction extends GlobalPaintAction {

	private DataFromPoint fromPoint;
	private DataToPoint toPoint;
	private DataLineSegment dataSeg;

	public void setFromPoint(DataFromPoint inputFromPt) {
		fromPoint = inputFromPt;
	}

	public void setToPoint(DataToPoint inputToPt) {
		toPoint = inputToPt;
	}

	public DataLineSegment getLineSeg() {

		return dataSeg;

	}

	@Override
	protected void execute(PaintPanel panel) {

		try{fromPoint.setExpectedType(toPoint.getExpectedType());}
		
		catch(ClassCastException e){
			if (!toPoint.getExpectedType().canBeAssignedFrom(fromPoint.getExpectedType())) {
				
				int result = JOptionPane.showConfirmDialog(panel,
						"The source type is " + fromPoint.getExpectedType() + ", the destination type is "
								+ toPoint.getExpectedType() + ". Do you want to proceed and create the connection anyway?",

						"Type Mismatch", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.NO_OPTION) {
					return;
				}

		}
		
		}

		DataLineSegment seg = new DataLineSegment(fromPoint, toPoint);
		panel.addPaintComponent(seg);

		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {

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

			@Override
			protected String commandName() {
				return "construct dataLineSegment";
			}

			@Override
			protected String commandDescription() {
				return "construct a line segment that flows data";
			}

		});
	}

}
