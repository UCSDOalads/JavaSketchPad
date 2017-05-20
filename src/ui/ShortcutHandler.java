package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import actions.AddDataDisplayBoxAction;
import actions.AddDataInputBoxAction;
import actions.AddLazyJavaClassAction;
import actions.FileSaveAs;
import actions.RemovePaintComponent;

/**
 * Shortcuts for some actions
 * 
 * Ctrl + s: save
 * Ctrl + r: remove
 * Ctrl + i: add input box
 * Ctrl + o: add output display box
 * Ctrl + c: add a class
 * @author Xiangyi Gong
 */
public class ShortcutHandler extends KeyAdapter {
	
	private PaintPanel panel;
	
	public ShortcutHandler(PaintPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// CTRL is not being pressed, return
		if ((e.getModifiers() & KeyEvent.CTRL_MASK) == 0) {
			return;
		}
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
				save();
				break;
			case KeyEvent.VK_C:
				addClass();
				break;
			case KeyEvent.VK_R:
				remove();
				break;
			case KeyEvent.VK_I:
				addInput();
				break;
			case KeyEvent.VK_O:
				addOutput();
				break;
		}
		
	}

	
	private void save() {
		FileSaveAs action = new FileSaveAs(panel);
		if (action.canPerformAction()) {
			action.performAction();
		}
	}
	
	private void remove() {
		RemovePaintComponent action = new RemovePaintComponent(panel);
		if (action.canPerformAction()) {
			action.performAction();
		}
	}
	
	private void addInput() {
		AddDataInputBoxAction action = new AddDataInputBoxAction(panel);
		if (action.canPerformAction()) {
			action.performAction();
		}
	}
	
	private void addOutput() {
		AddDataDisplayBoxAction action = new AddDataDisplayBoxAction(panel);
		if (action.canPerformAction()) {
			action.performAction();
		}
	}
	
	private void addClass() {
		AddLazyJavaClassAction action = new AddLazyJavaClassAction(panel);
		if (action.canPerformAction()) {
			action.performAction();
		}
	}
	
}
