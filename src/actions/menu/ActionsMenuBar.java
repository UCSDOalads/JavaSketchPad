package actions.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import actions.AddDataDisplayBoxAction;
import actions.AddDataInputBoxAction;
import actions.AddHaskellComponent;
import actions.AddHaskellEvaluatorComponentAction;
import actions.AddInstanceMethodAction;
import actions.AddInstanceOperationAction;
import actions.AddLazyJavaClassAction;
import actions.AddLazyJavaConstructorAction;
import actions.AddLazyJavaFieldsComponentAction;
import actions.AddLazyJavaMethodComponentAction;
import actions.AddTextBoxAction;
import actions.ConstructDataLineSegmentAction;
import actions.ConstructLineSegmentAction;
import actions.EditAnnotationSizeAction;
import actions.ExecuteInstanceConstructorAction;
import actions.ExecuteInstanceMethodAction;
import actions.ExecuteScriptAction;
import actions.FileOpen;
import actions.FileSaveAs;
import actions.GeneratePolygonSourceJava;
import actions.InputDataForDataInputBoxAction;
import actions.MenuBarPaintAction;
import actions.RedoAction;
import actions.RemoveAnnotationAction;
import actions.RemovePaintComponent;
import actions.ShowHistory;
import actions.UndoAction;
import actions.UpdateDataDisplayBoxAction;
import actions.ZoomInAction;
import actions.ZoomOutAction;
import actions.singleinstanceoperations.AddAnnotationAction;
import actions.singleinstanceoperations.SetPointSizeOperation;
import actions.singleinstanceoperations.UpdateFontSizeOperation;
import painttools.tools.SelectionToolListener;
import ui.PaintPanel;

public class ActionsMenuBar extends JMenuBar implements SelectionToolListener {

	public ActionsMenuBar(PaintPanel panel) {
		addAction(new GeneratePolygonSourceJava(panel));
		addAction(new ConstructLineSegmentAction(panel));
		addAction(new AddTextBoxAction(panel));
		addAction(new AddDataInputBoxAction(panel));
		addAction(new InputDataForDataInputBoxAction(panel));

		// data display
		addAction(new AddDataDisplayBoxAction(panel));
		addAction(new UpdateDataDisplayBoxAction(panel));

		// data segments
		addAction(new ConstructDataLineSegmentAction(panel));

		// java class
		addAction(new AddLazyJavaClassAction(panel));
		addAction(new AddLazyJavaConstructorAction(panel));
		addAction(new AddLazyJavaMethodComponentAction(panel));
		addAction(new AddLazyJavaFieldsComponentAction(panel));
		addAction(new AddInstanceOperationAction(panel));
		addAction(new AddInstanceMethodAction(panel));
		addAction(new ExecuteInstanceConstructorAction(panel));
		addAction(new ExecuteInstanceMethodAction(panel));

		// edit
		addAction(new RedoAction(panel));
		addAction(new UndoAction(panel));
		addAction(new ZoomInAction(panel));
		addAction(new ZoomOutAction(panel));

		// haskell
		addAction(new AddHaskellComponent(panel));
		addAction(new AddHaskellEvaluatorComponentAction(panel));

		// file
		addAction(new FileSaveAs(panel));
		addAction(new FileOpen(panel));

		// remove
		addAction(new RemovePaintComponent(panel));

		// edit
		addAction(new UpdateFontSizeOperation(panel));
		addAction(new SetPointSizeOperation(panel));
		addAction(new EditAnnotationSizeAction(panel));

		// script
		addAction(new ExecuteScriptAction(panel));

		// add data annotation
		addAction(new AddAnnotationAction(panel));
		addAction(new RemoveAnnotationAction(panel));

		// view
		addAction(new ShowHistory(panel));


		addAction(new EditAnnotationSizeAction(panel));

	}

	private void addAction(MenuBarPaintAction action) {
		String[] strings = action.locationString().split("/");
		JMenu insertionMenu = null;
		// look for existing i menus, determine where to insert
		for (int i = 0; i < getMenuCount(); i++) {
			JMenu menu = getMenu(i);
			if (menu.getText().equals(strings[0])) {
				insertionMenu = menu;
				break;
			}
		}
		// if not found, create a new menu
		if (insertionMenu == null) {
			JMenu newMenu = new JMenu(strings[0]);
			add(newMenu);
			insertionMenu = newMenu;
		}

		// do the similar steps above for k level
		for (int k = 1; k < strings.length - 1; k++) {
			boolean menuFound = false;
			for (int i = 0; i < insertionMenu.getItemCount(); i++) {

				// only check JMenu, exclude PaintActionMenuItem
				if (insertionMenu.getItem(i) instanceof JMenu) {
					JMenu menu = (JMenu) insertionMenu.getItem(i);
					if (menu.getText().equals(strings[k])) {
						insertionMenu = menu;
						menuFound = true;
						break;
					}
				}
			}
			// if not found, create a new menu

			if (!menuFound) {
				JMenu newMenu = new JMenu(strings[k]);
				insertionMenu.add(newMenu);
				insertionMenu = newMenu;
			}
		}

		// TODO Change here
		PaintActionMenuItem item = new PaintActionMenuItem(action, this);
		item.setEnabled(action.canPerformAction());
		item.setText(strings[strings.length - 1]);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				item.performAction();

			}
		});
		insertionMenu.add(item);
	}

	@Override
	public void selectionChanged() {
		updateEnableStatusForAllMenuItems();
	}

	public void updateEnableStatusForAllMenuItems() {
		for (int i = 0; i < getMenuCount(); i++) {
			JMenu menu = getMenu(i);
			recursiveUpdate(menu);
		}
	}

	private void recursiveUpdate(JMenu jitem) {
		for (int i = 0; i < jitem.getItemCount(); i++) {
			JMenuItem item = jitem.getItem(i);
			if (item instanceof PaintActionMenuItem)
				item.setEnabled(((PaintActionMenuItem) item)
						.getAssociatedAction().canPerformAction());
			else if (item instanceof JMenu)
				recursiveUpdate((JMenu) item);
		}
	}

}
