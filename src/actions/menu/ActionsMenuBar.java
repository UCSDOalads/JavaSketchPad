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
		JMenu insertionMenu = null;
		//look for existing j menus
		for (int i = 0; i < getMenuCount();i++) {
			JMenu menu = getMenu(i);
			if(menu.getText().equals(strings[0])){
				insertionMenu = menu;
				break;
			}
		}
		//create a new if not found
		if(insertionMenu == null){
			insertionMenu = new JMenu(strings[0]);
			this.add(insertionMenu);
		}
		
		//assume 2 level depth
		//TODO Change here
		PaintActionMenuItem item = new PaintActionMenuItem(action);
		item.setEnabled(action.canPerformAction());
		item.setText(strings[1]);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				action.performAction();
				
			}
		});
		
		insertionMenu.add(item);
		
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
