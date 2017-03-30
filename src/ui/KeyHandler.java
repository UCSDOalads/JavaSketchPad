package ui;

import java.awt.event.KeyEvent;

import paintcomponents.TextPaintComponent;

public class KeyHandler {

	private PaintPanel paintPanel;
	private String pendingCommand;
	private boolean inCommandMode;
	private TextPaintComponent component;

	public KeyHandler(PaintPanel paintPanel) {
		pendingCommand = "";
		this.paintPanel = paintPanel;
		this.component = new TextPaintComponent("", 0, 0);
		this.paintPanel.addPaintComponent(this.component);
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
		component.setY(height - rowHeight);
		paintPanel.repaint();
	}

}
