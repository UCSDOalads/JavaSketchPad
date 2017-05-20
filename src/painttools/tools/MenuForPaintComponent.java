package painttools.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import actions.PaintAction;
import actions.RemovePaintComponent;
import actions.menu.PaintActionMenuItem;
import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;
import ui.PaintPanel;

public class MenuForPaintComponent {
	public static JPopupMenu getMenuForPaintComponent(PaintComponent comp, PaintPanel panel) {
		JPopupMenu menu = new JPopupMenu();
		if( comp instanceof TextPaintComponent )
			addActionToMenu(menu, new RemovePaintComponent(panel));
		return menu;
			
	}
	private static void addActionToMenu(JPopupMenu menu, PaintAction action) {
		PaintActionMenuItem item = new PaintActionMenuItem(action);
		item.setEnabled(action.canPerformAction());
		item.setText(action.toString());
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				item.performAction();
				
			}
		});
		menu.add(item);
	}
}
