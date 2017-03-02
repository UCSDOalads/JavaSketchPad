package actions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataLineSegment;
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
		DataFromPoint fromPoint = (DataFromPoint) comps.get(0);
		DataToPoint toPoint = (DataToPoint) comps.get(1);
		if (!fromPoint.getExpectedType().equals(toPoint.getExpectedType())) {
			int result = JOptionPane.showConfirmDialog(panel,
					"The source type is " + fromPoint.getExpectedType()
							+ ", the destination type is "
							+ toPoint.getExpectedType()
							+ ". Do you want to proceed and create the connection anyway?",

					"Type Mismatch", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.NO_OPTION) {
				return;
			}

		}
		DataLineSegment seg = new DataLineSegment(fromPoint, toPoint);
		addLineSegment(seg);
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsMenuBarTitles.Data().Construct().Line_Segment()
				.toString();
	}

}
