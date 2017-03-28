package script;

import java.util.ArrayList;

import ui.PaintPanel;
import paintcomponents.data.DataDisplayPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;

/**
 * Interpret and execute scripts 
 * @author Xiaoquan Jiang
 */
public class Interpreter {

	private static final String ZOOM = "zoom";
	private static final String REMOVE = "remove";
	private static final String UPDATE = "update";
	private static final String GENERATE_POLYGON = "generatePolygon";
	private static final String FILE = "file";
	private static final String EDIT = "edit";
	private static final String CONSTRUCT = "Construct";
	private static final String ADD = "add";
	private PaintPanel panel;

	public Interpreter(PaintPanel panel) {
		this.panel = panel;
	}

	public void interpreteLine(String script) throws ExecutionErrorException {

		Tokenizer tokenizer = new Tokenizer(script);

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case ADD:
				new InterpreterAddActions(tokenizer, panel);
				break;
				
			case CONSTRUCT:
				new InterpreterConstructActions(tokenizer, panel);
				break;
				
			case EDIT:
				new InterpreterEditActions(tokenizer, panel);
				break;
				
			case FILE:
				new InterpreterFileActions(tokenizer, panel);
				break;
				
			case GENERATE_POLYGON:
				new InterpreterGeneratePolygonAction(tokenizer, panel);
				break;
				
			case UPDATE:
        new InterpreterUpdateActions(tokenizer, panel);
        break;
        
			case REMOVE:
        new InterpreterRemoveAction(tokenizer, panel);
        break;
        
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("Script is empty");
		}
	}


}
