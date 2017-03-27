package actions.singleinstanceoperations;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.SimplePoint;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

/**
 * A single instance operation responsible for setting the point size
 * @author chenzb
 *
 */
public class SetPointSizeOperation extends SingleInstanceOperation<SimplePoint> {

	public SetPointSizeOperation(PaintPanel panel) {
		super(panel);
	}


	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Point_Size().toString();
	}

	@Override
	protected void performActionOnInstance(SimplePoint instance) {
		InputManager.sharedInstance().askForInt(panel, new InputManagerDelegate<Integer>() {
			
			@Override
			public void didFinishInput(Integer input) {
				instance.setRadius(input);
			}
		});
		
	}


	@Override
	protected Class<SimplePoint> getGenericClassType() {
		return SimplePoint.class;
	}

}
