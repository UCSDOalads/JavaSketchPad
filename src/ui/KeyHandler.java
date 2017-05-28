package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import script.ExecutionErrorException;
import script.Interpreter;

public class KeyHandler implements KeyListener {
	
	public static final String COMMAND_LINE_DISPLAY = "Press : to enter commands";
	private static final String PROMPT = "";

	Color color = new Color(150, 150, 150);
	private PaintPanel paintPanel;
	private boolean inCommandMode;
	Interpreter interpreter ;
	private boolean isCurrentDirectionNext;
	
	JTextField textField;
	
	ListIterator<String> commandHistoryIter;
	
	
	
	public KeyHandler(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
		
		interpreter = new Interpreter(paintPanel);
		
		
		this.textField = new JTextField(COMMAND_LINE_DISPLAY);
		this.paintPanel.add(textField, BorderLayout.SOUTH);
		
		this.textField.addKeyListener(this);
		
		textField.setBackground(color);
		textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		//click and make text disappear
		textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	if (textField.getText().isEmpty() || 
            			textField.getText().matches(COMMAND_LINE_DISPLAY)){
                	enterCommandMode();
                }
            }
        });
		
		textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                	exitCommandMode();
                }
            }
        });
		
		this.commandHistoryIter = new LinkedList<String>().listIterator();
		
		
//		exitCommandMode();
	}

	private void enterCommandMode() {
		inCommandMode = true;
		textField.requestFocusInWindow();
		textField.setBackground(Color.WHITE);
		textField.setText(PROMPT);
	}

	private void exitCommandMode() {
		inCommandMode = false;
		paintPanel.requestFocusInWindow();
		textField.setBackground(color);
		textField.setText(COMMAND_LINE_DISPLAY);
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
