package actions;

import java.util.ArrayList;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public class RemoveAnnotationAction extends PaintAction{

	public RemoveAnnotationAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		if(items.size() != 1){
			return false;
		}
		if(items.get(0).getOptionalAnnotation() == null){
			return false;
		}
		return true;
	}

	@Override
	public void performAction() {
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		items.get(0).setOptionalAnnotation(null);
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Annotations().Remove().toString();
	}

}
