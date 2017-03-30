package actions;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;
import ui.helper.classsearch.ClassSearchFrame;
import ui.helper.classsearch.ClassSearchFrameDelegateInterface;
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
		InputManager im = new InputManager();
		im.askForClass(panel,new InputManagerDelegate<Class>() {
			
			@Override
			public void didSelectClass(String classname) {
				
				try {
					Class classObj = Class.forName(classname);
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

						@Override
						protected String commandName() {
							return "add lazy javaClass";
						}

						@Override
						protected String commandDescription() {
							return "add a java class component";
						}
					});
					panel.repaint();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(panel,
							classname + " :: Class Not Found");
				}

			public void didFinishInput(Class input) {
				ClassPaintComponent comp = new ClassPaintComponent(input,
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

					@Override
					protected String commandName() {
						return "add lazy javaClass";
					}

					@Override
					protected String commandDescription() {
						return "add a java class component";
					}
				});
				panel.repaint();

			}
		} );
			
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
