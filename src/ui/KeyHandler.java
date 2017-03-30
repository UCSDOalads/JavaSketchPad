package ui;

import java.awt.event.KeyEvent;

import paintcomponents.TextPaintComponent;
import script.ExecutionErrorException;
import script.Interpreter;

public class KeyHandler {

	private static final int VERTICAL_OFFSET = 5;
	private PaintPanel paintPanel;
	private String pendingCommand;
	private boolean inCommandMode;
	private TextPaintComponent component;
	Interpreter interpreter ;
	public KeyHandler(PaintPanel paintPanel) {
		pendingCommand = "";
		this.paintPanel = paintPanel;
		
		//initialize text component
		this.component = new TextPaintComponent("", 0, 0);
		this.paintPanel.addPaintComponent(this.component);
		
		interpreter = new Interpreter(paintPanel);
		
		
		//not in command
		inCommandMode = false;
	}

	public void keyPressed(KeyEvent e) {

		// backspace
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && inCommandMode) {
			if (pendingCommand.length() != 0) {
				pendingCommand = pendingCommand.substring(0,
						pendingCommand.length() - 1);
			} else {
				inCommandMode = false;
			}

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && inCommandMode) {

			executeCommand(pendingCommand);
			pendingCommand = "";
			inCommandMode = false;
		} else {

			char keyChar = e.getKeyChar();

			if (keyChar == ':') {
				pendingCommand = "";
				inCommandMode = true;
			} else if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED
					&& inCommandMode)/* if(e.isActionKey()) */{
				pendingCommand += e.getKeyChar();
			}

		}

		update();

	}

	private void executeCommand(String pendingCommand2) {

		try {
			interpreter.interpreteLine(pendingCommand2);
		} catch (ExecutionErrorException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		if (inCommandMode) {
			component.setDisplayingText(": " + pendingCommand);

		} else {
			component.setDisplayingText(pendingCommand);
		}
		int height = paintPanel.getHeight();
		int rowHeight = component.getRowHeight();
		component.setX(0);
		component.setY(height - rowHeight - VERTICAL_OFFSET);
		paintPanel.repaint();
	}

}
