package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import actions.PaintAction;
import actions.RemovePaintComponent;
import actions.add.AddConstructor;
import actions.add.AddFields;
import actions.add.AddMethod;
import actions.menu.PaintActionMenuItem;
import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import ui.PaintPanel;

public class MenuForPaintComponent {
	public static JPopupMenu getMenuForPaintComponent(PaintComponent comp, PaintPanel panel) {
		JPopupMenu menu = new JPopupMenu();
		if( comp instanceof TextPaintComponent )
			addActionToMenu(menu, new RemovePaintComponent(panel));
		if( comp instanceof ClassPaintComponent ) {
			Constructor[] ctr = ((ClassPaintComponent) comp).getDisplayingClass().getConstructors();
			for( int i = 0; i < ctr.length; i++) {
				addActionToMenu(menu, new AddConstructor(panel, ctr[i]));
			}
			Method[] method = ((ClassPaintComponent) comp).getDisplayingClass().getMethods();
			for( int i = 0; i < method.length; i++) {
				addActionToMenu(menu, new AddMethod(panel, method[i]));
			}
			addActionToMenu(menu, new AddFields(panel, ((ClassPaintComponent) comp).getDisplayingClass()));
		}

		return menu;
			
	}
	private static void addActionToMenu(JPopupMenu menu, PaintAction action) {
		String[] string = action.locationString().split("/");
		if(string.length == 1) {
			PaintActionMenuItem item = new PaintActionMenuItem(action);
			item.setEnabled(action.canPerformAction());
			item.setText(action.locationString());
			item.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					item.performAction();	
				}
			});
			menu.add(item);
		}
		
		//TODO This code is copied from ActionMenuBar, consider change
		else {
			JComponent insertionMenu = menu;
			for( int k = 0; k < string.length-1; k++) {
				boolean menuFound = false;
				for (int i = 0; i < insertionMenu.getComponentCount();i++) {
					
					// only check JMenu, exclude PaintActionMenuItem
					if(insertionMenu.getComponent(i) instanceof JMenu ) {
						JMenu existingMenu = (JMenu)insertionMenu.getComponent(i);
						if(existingMenu.getText().equals(string[k])){
							insertionMenu = existingMenu;
							menuFound = true;
							break;
						}
					}
				}
				// if not found, create a new menu

				if( !menuFound ) {
					JMenu newMenu = new JMenu(string[k]);
					insertionMenu.add(newMenu);
					insertionMenu = newMenu;
				}
			}
			PaintActionMenuItem item = new PaintActionMenuItem(action);
			item.setEnabled(action.canPerformAction());
			item.setText(string[string.length-1]);
			item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					item.performAction();
					
				}
			});
			insertionMenu.add(item);
			
		}
	}
}
