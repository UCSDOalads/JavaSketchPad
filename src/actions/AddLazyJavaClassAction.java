package actions;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class AddLazyJavaClassAction extends MenuBarPaintAction {

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
<<<<<<< HEAD
		im.askForClass(panel,new InputManagerDelegate<Class>() {
			
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

			}
=======
		im.askForClass(panel, new InputManagerDelegate<Class>() {

			@Override
>>>>>>> 8aa58ac4b86c1ea26278a3423379212bc90329af
			public void didFinishInput(Class input) {
				AddLazyJavaClassGlobalAction assiciatedAction 
				= (AddLazyJavaClassGlobalAction) ActionName.ADD_LAZY_JAVA_CLASS_ACTION
						.getAssiciatedAction();
				assiciatedAction.setClassToCreate(input);
				GlobalPaintActionExecuter.getSharedInstance().execute(assiciatedAction, panel);

<<<<<<< HEAD
					@Override
					protected String commandDescription() {
						return "add a java class component";
					}
				});
				panel.repaint();

=======
>>>>>>> 8aa58ac4b86c1ea26278a3423379212bc90329af
			}
		});

	}
	

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Lazy().Add().Java_Class().toString();
	}

}
