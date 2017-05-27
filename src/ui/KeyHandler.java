package ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JTextField;

import script.ExecutionErrorException;
import script.Interpreter;

public class KeyHandler implements KeyListener {

	//the prompt does not work, i.e. focusing issues
	private static final String PROMPT = "";
	private PaintPanel paintPanel;
	private boolean inCommandMode;
	Interpreter interpreter ;
	private boolean isCurrentDirectionNext;
	
	
	JTextField textField;
	
	ListIterator<String> commandHistoryIter;
	
	
	
	public KeyHandler(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
		
		interpreter = new Interpreter(paintPanel);
		
		
		this.textField = new JTextField("Press : to enter commands");
		this.paintPanel.add(textField, BorderLayout.SOUTH);
		
		this.textField.addKeyListener(this);
		
		this.commandHistoryIter = new LinkedList<String>().listIterator();
		
		
//		exitCommandMode();
	}

	private void enterCommandMode() {
		inCommandMode = true;
		textField.setVisible(true);
		textField.requestFocusInWindow();
		textField.setText(PROMPT);
	}

	private void exitCommandMode() {
		inCommandMode = false;
		textField.setVisible(false);
		paintPanel.requestFocusInWindow();
	}

	private void executeCommand(String pendingCommand2) {

		try {
			interpreter.interpreteLine(pendingCommand2);
		} catch (ExecutionErrorException e) {
			e.printStackTrace();
		}
	}

	public void keyEntered(KeyEvent e) {
		char keyChar = e.getKeyChar();
	
		if (keyChar == ':') {
			enterCommandMode();
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == '\n'){
			//get rid of the prompt
			
			//record command

			while(commandHistoryIter.hasNext()){
				this.commandHistoryIter.next();
			}
			isCurrentDirectionNext = true;
			commandHistoryIter.add(textField.getText());
			
			executeCommand(textField.getText().substring(PROMPT.length()));
			exitCommandMode();
		} else if (e.getKeyChar() == '\b' && textField.getText().length() < PROMPT.length()){
			//do not allow prompt to be deleted
			textField.setText(PROMPT);
			
			//if it is escape
		} 
		
	}

	public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			exitCommandMode();

			//if it is up arrow, go back in history
		} else if (e.getKeyCode() == KeyEvent.VK_UP ){
			if(commandHistoryIter.hasPrevious()){
				if(isCurrentDirectionNext){
					commandHistoryIter.previous();
				}
				isCurrentDirectionNext = false;
				this.textField.setText(commandHistoryIter.previous());
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN ){
			if(commandHistoryIter.hasNext()){
				if(!isCurrentDirectionNext){
					commandHistoryIter.next();
				}
				isCurrentDirectionNext = true;
				this.textField.setText(commandHistoryIter.next());
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
