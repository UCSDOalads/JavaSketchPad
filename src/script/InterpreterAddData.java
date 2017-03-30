package script;

import paintcomponents.PaintComponent;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataInputTextfieldPaintComponent;
import ui.PaintPanel;
import actions.AddDataDisplayBoxAction;
import actions.AddDataInputBoxAction;
import actions.AddTextBoxAction;
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
		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);
		action.performAction();
		return panel.getPaintComponents().get(panel.getPaintComponents().size() -1);
	}

	private PaintComponent performAddInputBoxAction() {
		AddTextBoxAction action = new AddTextBoxAction(panel);
		action.performAction();
		return panel.getPaintComponents().get(panel.getPaintComponents().size() -1);
	}
}
