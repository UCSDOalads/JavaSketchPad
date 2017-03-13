package actions.singleinstanceoperations;

import javax.sound.sampled.Line;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.LineSegment;
import ui.PaintPanel;
import ui.general.InputManager;
import ui.general.InputManagerDelegate;

public class SetLineSegmentWidthOperation extends SingleInstanceOperation<LineSegment> {

	public SetLineSegmentWidthOperation(PaintPanel panel) {
		super(panel);
	}


	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Edit().Line_Segment_Width().toString();
	}

	@Override
	protected void performActionOnInstance(LineSegment instance) {
		InputManager.sharedInstance().askForDouble(panel, new InputManagerDelegate<Double>() {
			
			@Override
			public void didFinishInput(Double input) {
				instance.setStrokeWidth(input);
			}
		});
		
	}

	@Override
	protected Class<LineSegment> getGenericClassType() {
		return LineSegment.class;
	}

}
