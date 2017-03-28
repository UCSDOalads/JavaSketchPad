package script;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

public class InterpreterUpdateActions {
	
	private static final String INPUT_BOX = "inputBox";
	private static final String DATA_BOX = "dataBox";
	private PaintPanel panel;

	public InterpreterUpdateActions(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case DATA_BOX:
				performUpdateDataBox();
				break;

			case INPUT_BOX:
				performUpdateinputBox();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performUpdateDataBox() {
		DataDisplayPaintComponent comp = (DataDisplayPaintComponent) panel.getSelectTool().getSelectedComponents().get(0) ;
		try {
			comp.updateDisplayText();
			//push action to the manager
			SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
				
				@Override
				public void undoAction() {
					comp.remove(panel);
					panel.repaint();
				}
				
				@Override
				public void redoAction() {
					panel.addPaintComponent(comp);
					panel.repaint();
				}
			});
			panel.repaint();
		} catch (NoSuchElementException | NoConnectingLineSegmentException
				| DataFromPointNoDataProviderException
				| DataFromPointProviderCannotProvideDataException e) {
			Logger.getGlobal().warning(e.toString());
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	private void performUpdateinputBox() {
		DataInputTextfieldPaintComponent inputComp = (DataInputTextfieldPaintComponent) panel.getSelectTool().getSelectedComponents().get(0);
		String s = JOptionPane.showInputDialog("Please specify the message to push to the data input");
		inputComp.inputData(s);
		// add action to undo redo manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
			
			@Override
			public void undoAction() {
				inputComp.remove(panel);
				panel.repaint();
			}
			
			@Override
			public void redoAction() {
				panel.addPaintComponent(inputComp);
				panel.repaint();
			}
		});
		panel.repaint();
	}

}
