package actions.singleinstanceoperations;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.TextPaintComponent;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class UpdateFontSizeOperation extends SingleInstanceOperation<TextPaintComponent>{

	public UpdateFontSizeOperation(PaintPanel panel) {
		super(panel);
	}

	@Override
	protected void performActionOnInstance(TextPaintComponent instance) {
		 InputManager.sharedInstance().askForFloat(panel, new InputManagerDelegate<Float>() {
			
			@Override
			public void didFinishInput(Float input) {
				instance.setFontSize(input);
				panel.repaint();
			}
		});
		
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Font_Size().toString();
	}

	@Override
	protected Class<TextPaintComponent> getGenericClassType() {
		return TextPaintComponent.class;
	}

	
	
	

}
