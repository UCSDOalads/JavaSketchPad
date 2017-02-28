package actions;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.DataDisplayPaintComponent;
import paintcomponents.DataFromPointNoDataProviderException;
import paintcomponents.DataFromPointProviderCannotProvideDataException;
import paintcomponents.NoConnectingLineSegmentException;
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
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}

	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Display_Box().Update().toString();
	}

}
