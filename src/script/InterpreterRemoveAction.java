package script;

import java.util.ArrayList;
import paintcomponents.PaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Interpret and execute 'remove' script 
 * @author Xiaoquan Jiang
 */
public class InterpreterRemoveAction {
	
	private PaintPanel panel;
	
	public InterpreterRemoveAction(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;
		performRemove();
	}

	private void performRemove() {
		ArrayList<PaintComponent> comps = new ArrayList<>();
		for ( PaintComponent comp: panel.getSelectTool().getSelectedComponents())	{
			comps.add(comp);
		}
		for( PaintComponent comp: comps ) comp.remove(panel);
		
		//push action to the manager
		SharedUndoRedoActionManager.getSharedInstance().pushUndoableAction(new UndoRedoableInterface() {
				
			@Override
			public void undoAction() {
				for( PaintComponent comp: comps )
					panel.addPaintComponent(comp);
			}
			@Override
			public void redoAction() {
				for( PaintComponent comp: comps ) 
					comp.remove(panel);
			}
		});
		panel.repaint();
	}
}
