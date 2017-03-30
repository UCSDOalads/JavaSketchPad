package script;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.PaintComponent;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Interpret and execute 'update' scripts
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterUpdateActions {

	private static final String CAN_NOT_PERFORM_ACTION = "Can not perform action";
	private static final String INPUT_BOX = "inputBox";
	private static final String DATA_BOX = "dataBox";
	private PaintPanel panel;
	private PaintComponent comp;
	private String token;

	public InterpreterUpdateActions(Tokenizer tokenizer, PaintPanel panel) throws ExecutionErrorException {
		this.panel = panel;

		// check if name of a component is specified
		if (tokenizer.hasNext()) {
			token = tokenizer.next();

			// select component
			if (ComponentMap.map.containsKey(token)) {
				comp = ComponentMap.map.get(token);
			} else {
				throw new ExecutionErrorException("invalid conponent name");
			}
		} else {
			ArrayList<PaintComponent> comps = panel.getSelectTool().getSelectedComponents();
			if (comps.size() == 1) {
				comp = comps.get(0);
			} else {
				throw new ExecutionErrorException("invalid selection");
			}
		}

		// check if selected component can be updated
		if (comp instanceof DataDisplayPaintComponent) {
			performUpdateDataBox();
		} else if (comp instanceof DataInputTextfieldPaintComponent) {
			performUpdateinputBox();
		} else {
			throw new ExecutionErrorException("invalid type");
		}
	}

	private void performUpdateDataBox() {
		try {
			((DataDisplayPaintComponent) comp).updateDisplayText();
			// push action to the manager
			SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {

				public void undoAction() {
					comp.remove(panel);
					panel.repaint();
				  if (ComponentMap.map.containsValue(comp)) {
				  	
				  }
				}

				public void redoAction() {
					panel.addPaintComponent(comp);
					panel.repaint();
				}
			});
			panel.repaint();
		} catch (NoSuchElementException | NoConnectingLineSegmentException | DataFromPointNoDataProviderException
				| DataFromPointProviderCannotProvideDataException e) {
			Logger.getGlobal().warning(e.toString());
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	private void performUpdateinputBox() {
		String s = JOptionPane.showInputDialog("Please specify the message to push to the data input");
		((DataInputTextfieldPaintComponent) comp).inputData(s);
		// add action to undo redo manager
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
	}
}
