package actions.global.globalactions;

import java.awt.HeadlessException;

import script.ExecutionErrorException;
import script.Interpreter;
import ui.PaintPanel;
import actions.global.GlobalPaintAction;

public class ExecuteScriptGlobalAction extends GlobalPaintAction {

	String command;

	@Override
	protected void execute(PaintPanel panel) {

		Interpreter interpreter = new Interpreter(panel);

		System.out.println("Enter script:");

		try {
			interpreter.interpreteLine(command);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
