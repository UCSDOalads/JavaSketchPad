package actions;

import java.awt.Dimension;

import ui.PaintPanel;
import ui.helper.historyui.undoredoLog.UndoredoDialog;

public class ShowHistory extends PaintAction {
	

	public ShowHistory(PaintPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canPerformAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub

		UndoredoDialog.sharedInstance().setVisible(true);
		
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return "View/Show/History";
	}
	
}
