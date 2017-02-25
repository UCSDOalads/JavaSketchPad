package actions.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import actions.ConstructLineSegmentAction;
import actions.GeneratePolygonSourceJava;
import actions.PaintAction;
import painttools.tools.SelectionToolListener;
import ui.PaintPanel;

public class ActionsMenuBar extends JMenuBar implements SelectionToolListener{
	
	public ActionsMenuBar(PaintPanel panel){
		addAction(new GeneratePolygonSourceJava(panel));
		addAction(new ConstructLineSegmentAction(panel));

	}

	private void addAction(PaintAction action) {
		String[] strings = action.locationString().split("/");
		Object insertionMenu = this;
		//look for existing j menus
		for( int k = 0; k < strings.length-1; k++) {
			for (int i = 0; i < menuCount( insertionMenu );i++) {
				JMenuItem menu = obtainMenu(insertionMenu, i);
				if(menu.getText().equals(strings[k])){
					insertionMenu = menu;
					break;
				}
			}
			//create a new if not found
			JMenu toInsert = new JMenu(strings[k]);
			insertMenu( insertionMenu, toInsert );
			insertionMenu = toInsert;
		}
		//assume 2 level depth
		//TODO Change here
		PaintActionMenuItem item = new PaintActionMenuItem(action);
		item.setEnabled(action.canPerformAction());
		item.setText(strings[strings.length-1]);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				action.performAction();
				
			}
		});
		
		insertMenu( insertionMenu, item );		
	}
	private int menuCount( Object a ) {
		if( a instanceof JMenu) {
			return ((JMenu) a).getItemCount();
		}
		if( a instanceof JMenuBar) {
			return ((JMenuBar) a).getMenuCount();
		}
		return -1;
	}
	private JMenuItem obtainMenu( Object a, int index  ) {
		if( a instanceof JMenu) {
			return ((JMenu) a).getItem(index);
		}
		if( a instanceof JMenuBar) {
			return ((JMenuBar) a).getMenu(index);
		}
		return null;
	}	
	private void insertMenu( Object a, JMenuItem toInsert ) {
		if( a instanceof JMenu) {
			((JMenu) a).add(toInsert);
		}
		if( a instanceof JMenuBar) {
			((JMenuBar) a).add(toInsert);
		}
	}

	@Override
	public void selectionChanged() {
		for (int i = 0; i < getMenuCount(); i++) {
			JMenu menu = getMenu(i);
			for(int j = 0; j < menu.getItemCount(); j++){
				PaintActionMenuItem item = (PaintActionMenuItem) menu.getItem(j);
				item.setEnabled(item.getAssociatedAction().canPerformAction());
			}
			
		}
		
	}

	
	

}
