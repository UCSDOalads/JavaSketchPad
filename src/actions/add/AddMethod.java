package actions.add;

import java.lang.reflect.Method;

import actions.menu.ActionsPopupMenuTitles;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import ui.PaintPanel;

public class AddMethod extends AddOperation<MethodPaintComponent> {

	private Method method;
	
	public AddMethod(PaintPanel panel, Method method) {
		super(panel);
		// TODO Auto-generated constructor stub
		this.method = method;
	}

	@Override
	protected MethodPaintComponent getPaintComponentToAdd() {
		// TODO Auto-generated method stub
		return new MethodPaintComponent(method, 0, 0);
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsPopupMenuTitles.Method().append(method.toString()).toString();
	}
	
}
