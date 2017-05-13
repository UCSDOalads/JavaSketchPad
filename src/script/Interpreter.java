package script;

import ui.PaintPanel;

/**
 * Interpret and execute scripts
 * 
 * @author Xiaoquan Jiang
 */
public class Interpreter {

	private static final String SELECT = "select";
	private static final String ZOOM = "zoom";
	private static final String REMOVE = "remove";
	private static final String UPDATE = "update";
	private static final String GENERATE_POLYGON = "generatePolygon";
	private static final String FILE = "file";
	private static final String EDIT = "edit";
	private static final String CONSTRUCT = "construct";
	private static final String ADD = "add";
	private static final String NAME = "name";
	protected PaintPanel panel;

	public Interpreter(PaintPanel panel) {
		this.panel = panel;
	}

	public void interpreteLine(String script) throws ExecutionErrorException {

		Tokenizer tokenizer = new Tokenizer(script);

		// interpret the first token and pass the tokenizer to a sub interpreter
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

			case SELECT:
				new InterpreterSelectAction(tokenizer, panel);
				break;

			case NAME:
				new InterpreterNameAction(tokenizer, panel);
				break;

			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("Script is empty");
		}
	}
}

/*
 * Scripts: add data displayBox inputBox haskell component haskellCompoment lazy
 * javaClass javaConstructor javaFieldsComponent javaMethodComponent textBox
 * construct dataLineSegment lineSegment edit redo undo file saveAs == save open
 * generatePolygon update dataBox inputBox remove
 */