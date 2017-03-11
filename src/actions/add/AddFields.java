package actions.add;

import actions.menu.ActionsPopupMenuTitles;
import paintcomponents.PaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import ui.PaintPanel;

public class AddFields extends AddOperation{

	private Class displayingClass;
	
	public AddFields(PaintPanel panel, Class displayingClass) {
		super(panel);
		// TODO Auto-generated constructor stub
		this.displayingClass = displayingClass;
	}

	@Override
	protected PaintComponent getPaintComponentToAdd() {
		// TODO Auto-generated method stub
		return new FieldsPaintComponent(displayingClass, 0, 0);
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsPopupMenuTitles.Fields().toString();
	}

}
