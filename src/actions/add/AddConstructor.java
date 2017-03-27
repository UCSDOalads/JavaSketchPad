package actions.add;

import java.lang.reflect.Constructor;

import actions.menu.ActionsMenuBarTitles;
import actions.menu.ActionsPopupMenuTitles;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import ui.PaintPanel;

public class AddConstructor extends AddOperation<ClassConstructorPaintComponent> {

	private Constructor constructor;

	public AddConstructor(PaintPanel panel, Constructor constructor) {
		super(panel);
		// TODO Auto-generated constructor stub
		this.constructor = constructor;
	}

	@Override
	protected ClassConstructorPaintComponent getPaintComponentToAdd() {
		// TODO Auto-generated method stub
		return new ClassConstructorPaintComponent(constructor, 0, 0);
	}

	@Override
	public String locationString() {
		// TODO Auto-generated method stub
		return ActionsPopupMenuTitles.Constructor().append(constructor.toString()).toString();
	}

}
