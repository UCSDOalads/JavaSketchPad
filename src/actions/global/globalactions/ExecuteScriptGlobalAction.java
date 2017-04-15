package actions.global.globalactions;

import java.awt.HeadlessException;

import script.ExecutionErrorException;
import script.Interpreter;
import ui.PaintPanel;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.global.GlobalPaintAction;

public class ExecuteScriptGlobalAction extends GlobalPaintAction {

	String command;

	@Override
	protected void execute(PaintPanel panel) {

		Interpreter interpreter = new Interpreter(panel);

		try {
			interpreter.interpreteLine(command);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ExecutionErrorException e) {
			e.printStackTrace();
		}
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	protected UndoRedoableInterface getUndoRedoBlock() {
		// TODO Auto-generated method stub
		return null;
	}

}
