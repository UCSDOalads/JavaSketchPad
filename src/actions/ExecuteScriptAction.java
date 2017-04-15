package actions;

import java.awt.HeadlessException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;
import script.ExecutionErrorException;
import script.Interpreter;

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
		Interpreter interpreter = new Interpreter(panel);
		/*Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter script:");
		while (scanner.hasNextLine()) {
			try {
				interpreter.interpreteLine(scanner.nextLine());
			} catch (ExecutionErrorException e) {
				System.out.println("Invalid script");
			}
			System.out.println("Enter script:");
		}
		scanner.close();
		*/
		try {
      interpreter.interpreteLine(JOptionPane.showInputDialog(panel, "Enter Script: "));
    } catch (HeadlessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionErrorException e) {
      e.printStackTrace();
    }
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Script().Enter_Script().toString();
	}

}
