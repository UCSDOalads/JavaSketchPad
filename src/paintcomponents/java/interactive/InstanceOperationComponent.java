package paintcomponents.java.interactive;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.w3c.dom.Element;

import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataTextIOPaintComponent;
import painttools.tools.SelectTool;
import ui.PaintPanel;

public class InstanceOperationComponent extends DataTextIOPaintComponent 
		implements DataFromPointDataProvider {
	
	private int height;
	private int unitHeight;
	
	private ClassConstructorPaintComponent ctorPC;
	private ArrayList<MethodPaintComponent> methods;
	private Object instance;
	
	public InstanceOperationComponent(Constructor displayingContructor,
			int x, int y) {
		super(displayingContructor.toString(), x, y);
		ctorPC = new ClassConstructorPaintComponent(displayingContructor, x, y);
		methods = new ArrayList<>();
		height = 0;
	}

	@Override
	public Object provideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
		return ctorPC.provideInformationToDataFromPoint(dataFromPoint);
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
		return ctorPC.canProvideInformationToDataFromPoint(dataFromPoint);
	}
	
	public void addMethodPaintComponent(Method method, PaintPanel panel) {
		if (height == 0) {
			height = this.getRowHeight() * this.getNumberOfRows();
			unitHeight = this.getRowHeight();
		}
		
		MethodPaintComponent methodComp = new MethodPaintComponent(
				method, instance, this.getX(), this.getY() + height);
		String[] rows = methodComp.getDisplayingText().split("\n");
		height += unitHeight * rows.length;
		methods.add(methodComp);
		panel.addPaintComponent(methodComp);
		panel.repaint();
	}
	

	public Class getDisplayingClass() {
		return ctorPC.getSelectedClass();
	}

	@Override
	public void translate(int i, int j) {
		// TODO Auto-generated method stub
		super.translate(i, j);
		ctorPC.translate(i, j);
		methods.forEach(e -> e.translate(i, j));
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		if (ctorPC.contains(x, y)) {
			return true;
		} else {
			for (MethodPaintComponent method : methods) {
				if (method.contains(x, y)) {
					return true;
				}
			}
		}
		return super.contains(x, y);
	}


	@Override
	public void select(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		for (MethodPaintComponent method : methods) {
			if (method.contains(x,  y)) {
				method.select(selectTool);
			}
		}
		super.select(selectTool);
	}


	@Override
	public void deselect(SelectTool selectTool) {
		int x = selectTool.getLastMouseEvent().getX();
		int y = selectTool.getLastMouseEvent().getY();
		for (MethodPaintComponent method : methods) {
			if (method.contains(x,  y)) {
				method.select(selectTool);
			}
		}
		super.deselect(selectTool);
	}

}
