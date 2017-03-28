package actions;

import java.util.Scanner;

import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;
import script.ExecutionErrorException;
import script.Interpreter;

public class EnterScriptAction extends PaintAction {
	
	public EnterScriptAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		Interpreter interpreter = new Interpreter(panel);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter script:");
		while (scanner.hasNextLine()) {
			System.out.println("Enter script:");
			try {
				interpreter.interpreteLine(scanner.nextLine());
			} catch (ExecutionErrorException e) {
				System.out.println("Invalid script");
			}
		}
		scanner.close();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Script().Enter_Script().toString();
	}

}
