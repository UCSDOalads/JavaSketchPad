package actions;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import ui.PaintPanel;

public class UpdateDataDisplayBoxAction extends PaintAction {

	public UpdateDataDisplayBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		if(panel.getSelectTool().getSelectedComponents().size() == 1){
			if(panel.getSelectTool().getSelectedComponents().get(0) instanceof DataDisplayPaintComponent){
				return true;
			}
		}
		return false;
	}

	@Override
	public void performAction() {
		DataDisplayPaintComponent comp = (DataDisplayPaintComponent) panel.getSelectTool().getSelectedComponents().get(0) ;
		try {
			comp.updateDisplayText();
			panel.repaint();
		} catch (NoSuchElementException | NoConnectingLineSegmentException
				| DataFromPointNoDataProviderException
				| DataFromPointProviderCannotProvideDataException e) {
			Logger.getGlobal().warning(e.toString());
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Display_Box().Update().toString();
	}

}
