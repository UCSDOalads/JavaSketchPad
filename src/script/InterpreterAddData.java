package script;

import paintcomponents.PaintComponent;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;

/**
 * Interpret and execute 'add data' scripts 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddData {
	private static final String INPUT_BOX = "inputBox";
	private static final String DISPLAY_BOX = "displayBox";
	private PaintPanel panel;
	private PaintComponent component;

	public InterpreterAddData(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {

		this.panel = panel;
		
		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case DISPLAY_BOX:
				component = performAddDisplayBoxAction();
				break;

			case INPUT_BOX:
				component = performAddInputBoxAction();
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
		
    // name and store the component added
    if (tokenizer.hasNext() && component != null) {
      ComponentMap.map.put(tokenizer.next(), component);
    }
	}

	private PaintComponent performAddDisplayBoxAction() {
		DataDisplayPaintComponent comp = new DataDisplayPaintComponent(
				"Data Display", panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);

		// push action to manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

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
		return comp;
	}

	private PaintComponent performAddInputBoxAction() {
		DataInputTextfieldPaintComponent comp = new DataInputTextfieldPaintComponent(
				"Data Input", panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(comp);

		// push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(
				new UndoRedoableInterface() {

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
		return comp;
	}
}
