package actions;

import java.util.ArrayList;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.ConstructDataLineSegmentGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataToPoint;
import ui.PaintPanel;

public class ConstructDataLineSegmentAction extends ConstructLineSegmentAction {

	public ConstructDataLineSegmentAction(PaintPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canPerformAction() {
		if (super.canPerformAction() == false)
			return false;
		// we must connect from a DataFromPoint to DataToPoint
		// assume ConstructLineSegment is doing correctly, there is two
		// corrently selected points
		ArrayList<PaintComponent> comps = this.panel.getSelectTool()
				.getSelectedComponents();
		// TODO IMPORTANT Generic Argument is erased, may cause unexpected
		// behavior when types dont match in the future
		if (comps.get(0) instanceof DataFromPoint
				&& comps.get(1) instanceof DataToPoint) {
			// allow connection only when no segment has no existing connections
			// to the data
			if (((DataToPoint) comps.get(1)).getLineSegment() == null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void performAction() {

		ArrayList<PaintComponent> comps = this.panel.getSelectTool()
				.getSelectedComponents();

		ConstructDataLineSegmentGlobalAction associatedAction = (ConstructDataLineSegmentGlobalAction) ActionName.CONSTRUCT_DATA_LINE_SEGMENT_ACTION
				.getAssiciatedAction();

		DataFromPoint fromPoint = (DataFromPoint) comps.get(0);
		DataToPoint toPoint = (DataToPoint) comps.get(1);

		associatedAction.setFromPoint((DataFromPoint) (comps.get(0)));
		associatedAction.setToPoint((DataToPoint) (comps.get(1)));
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);

		panel.repaint();

	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Data().Construct().Line_Segment()
				.toString();
	}

}
