package paintcomponents.java.interactive;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;
import paintcomponents.data.DataToPoint;
import painttools.tools.SelectTool;
import typesystem.JavaType;
import ui.PaintPanel;

public class InstanceOperationComponent extends DataTextIOPaintComponent 
		implements DataFromPointDataProvider {
	
	private int height;
	private int unitHeight;
	
	private ArrayList<MethodPaintComponent> methods;
	
	private Constructor displayingConstructor;
	private Object instance;
	
	public InstanceOperationComponent(Constructor displayingContructor,
			int x, int y) {
		super(displayingContructor.toString(), x, y);
		this.displayingConstructor = displayingContructor;
		init();
		methods = new ArrayList<>();
		height = 0;
	}
	
	private void init() {

		// parameters take place from line 1 to length
		Class[] paramTypes = displayingConstructor.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			addToPoint(i + 1, new JavaType(paramTypes[i]));
		}

		// constructed instance take line length+1
		addFromPoint(this, paramTypes.length + 1,
				new JavaType(this.displayingConstructor.getDeclaringClass()));
		
		// prepare String
		StringBuilder s = new StringBuilder();
		s.append(this.displayingConstructor.toString() + "\n");
		for (int i = 0; i < paramTypes.length; i++) {
			s.append("arg" + i + " :: " + paramTypes[i].getName() + "\n");
		}

		s.append("Constructed Instance >>>> " + "\n");
		setDisplayingText(s.toString());

	}
	
	@Override
	public Object provideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
		return instance;
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(DataFromPoint dataFromPoint) {
		return instance != null;
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
		return displayingConstructor.getDeclaringClass();
	}

	@Override
	public void translate(int i, int j) {
		// TODO Auto-generated method stub
		super.translate(i, j);
		methods.forEach(e -> e.translate(i, j));
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub

		for (MethodPaintComponent method : methods) {
			if (method.contains(x, y)) {
				return true;
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
				return;
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
	
	public void executeConstructor() {
		
		try {
			Object[] initargs = new Object[this.getToPoints().size()];
			for (int i = 0; i < initargs.length; i ++) {
				DataFromPoint fromP = 
						this.getToPoints().get(i).getLineSegment().getFromPoint();
				initargs[i] = fromP.getProvider().provideInformationToDataFromPoint(fromP);
			}
			instance = this.displayingConstructor.newInstance(initargs);
			methods.forEach(e -> e.setInstance(instance));
System.out.println("new instance: " + instance);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Not Valid Parameters");
			e.printStackTrace();
		}
	}

}
