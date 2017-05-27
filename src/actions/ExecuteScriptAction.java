package actions;

import javax.swing.JOptionPane;

import ui.PaintPanel;
import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ExecuteScriptGlobalAction;
import actions.menu.ActionsMenuBarTitles;

public class ExecuteScriptAction extends MenuBarPaintAction {

	public ExecuteScriptAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * 
		 * System.out.println("Enter script:"); while (scanner.hasNextLine()) {
		 * try { interpreter.interpreteLine(scanner.nextLine()); } catch
		 * (ExecutionErrorException e) { System.out.println("Invalid script"); }
		 * System.out.println("Enter script:"); } scanner.close();
		 */
		String command = JOptionPane.showInputDialog(panel, "Enter Script: ");
		ExecuteScriptGlobalAction assiciatedAction = (ExecuteScriptGlobalAction) ActionName.EXECUTE_SCRIPT_ACTION
				.getAssiciatedAction();

		assiciatedAction.setCommand(command);
		GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction,
				panel);

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Script().Enter_Script().toString();
	}

}
