package actions;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import ui.PaintPanel;

public class UpdateDataDisplayBoxAction extends MenuBarPaintAction {

	public UpdateDataDisplayBoxAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		
		if(panel.getSelectTool().getSelectedComponents().size() == 1){
			if(panel.getSelectTool().getSelectedComponents().get(0) instanceof DataDisplayPaintComponent){
				return ((DataDisplayPaintComponent) panel.getSelectTool().getSelectedComponents().get(0)).canUpdate();
			}
		}
		return false;
	}

	@Override
	public void performAction() {
		DataDisplayPaintComponent comp = (DataDisplayPaintComponent) panel.getSelectTool().getSelectedComponents().get(0) ;
		try {
			String original = comp.getDisplayingText();
			comp.updateDisplayText();
			//push action to the manager
			//TODO This may cause a bug. Redo only replaces text occurances.
			SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
				
				@Override
				public void undoAction() {
					comp.setDisplayingText(original);
					panel.repaint();
				}
				
				@Override
				public void redoAction() {
					try {
						comp.updateDisplayText();
					} catch (NoSuchElementException
							| NoConnectingLineSegmentException
							| DataFromPointNoDataProviderException
							| DataFromPointProviderCannotProvideDataException e) {
						e.printStackTrace();
					}
					panel.repaint();
				}

				@Override
				protected String commandName() {
					return "update dataBox";
				}

				@Override
				protected String commandDescription() {
					return "update the data display box using the connecting input";
				}
			});
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
