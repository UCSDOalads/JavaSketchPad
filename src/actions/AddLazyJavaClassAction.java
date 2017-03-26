package actions;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import actions.menu.PaintActionMenuItem;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

import ui.helper.ClassSearchFrame;
import ui.helper.ClassSearchFrameDelegateInterface;

public class AddLazyJavaClassAction extends PaintAction {

	public AddLazyJavaClassAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		ClassSearchFrame searchFrame = new ClassSearchFrame(this);
		searchFrame.setDelegate(new ClassSearchFrameDelegateInterface() {
			
			@Override
			public void didSelectClass(String classname) {
				// TODO Auto-generated method stub
				
			}
		});
		
		searchFrame.setVisible(true);
		searchFrame.setSize(new Dimension(300,200));
		
		panel.repaint();
	
	}
	
	/*
	 * Add a new class to the painting area when it receives a call
	 * from the search frame.
	 */
	public void perform(String name){
		String className = name;
			
			try {
				
				Class classObj = Class.forName(className);
				ClassPaintComponent comp = new ClassPaintComponent(classObj,
						panel.getWidth() / 2, panel.getHeight() / 2);
				panel.addPaintComponent(comp);
				// add action to undo redo manager
				SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
					
					@Override
					public void undoAction() {
						comp.remove(panel);
						panel.repaint();
					}
					
					@Override
					public void redoAction() {
						panel.addPaintComponent(comp);
						panel.repaint();
					}
				});
				panel.repaint();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(panel,
						className + " :: Class Not Found");
			}
	}
	
	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
