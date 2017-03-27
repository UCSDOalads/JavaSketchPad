package actions;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import actions.menu.ActionsMenuBarTitles;
import paintcomponents.PaintComponent;
import paintcomponents.TextPaintComponent;
import paintcomponents.annotations.TextAnnotation;
import paintcomponents.data.DataTextPaintComponent;
import ui.PaintPanel;

public class AddAnnotationAction extends PaintAction{

	public AddAnnotationAction(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		//get selected components
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		
		if(items.size() != 1){
			return false;
		}
		if(!(items.get(0) instanceof DataTextPaintComponent)){
			return false;
		}
		return true;
	}

	@Override
	public void performAction() {
		ArrayList<PaintComponent> items = panel.getSelectTool().getSelectedComponents();
		String annotations = JOptionPane
				.showInputDialog("Please specify the annotation of the component");
		TextAnnotation textAnnotation = new TextAnnotation(items.get(0), annotations);
		items.get(0).setOptionalAnnotation(textAnnotation);
		panel.addPaintComponent(textAnnotation);
		panel.repaint();
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.Data().Annotations().Add().toString();
	}


}
