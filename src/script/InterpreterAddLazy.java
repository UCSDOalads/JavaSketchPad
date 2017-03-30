package script;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import paintcomponents.PaintComponent;
import paintcomponents.java.lazy.ClassConstructorPaintComponent;
import paintcomponents.java.lazy.ClassPaintComponent;
import paintcomponents.java.lazy.FieldsPaintComponent;
import paintcomponents.java.lazy.MethodPaintComponent;
import actions.edit.undoredo.SharedUndoRedoActionManager;
import actions.edit.undoredo.UndoRedoableInterface;
import ui.PaintPanel;

/**
 * Interpret and execute 'add_lazy' scripts
 * 
 * @author Xiaoquan Jiang
 */
public class InterpreterAddLazy {

	private static final String JAVA_METHOD_COMPONENT = "javaMethodComponent";
	private static final String JAVA_FIELDS_COMPONENT = "javaFieldsComponent";
	private static final String JAVA_CONSTRUCTOR = "javaConstructor";
	private static final String JAVA_CLASS = "javaClass";
	private PaintPanel panel;
	private PaintComponent comp;
	private ClassPaintComponent classComp;
	private String className;
	private String compName;
	private boolean specified;

	public InterpreterAddLazy(Tokenizer tokenizer, PaintPanel panel) throws ExecutionErrorException {
		String token;
		String nextToken;
		this.panel = panel;

		if (tokenizer.hasNext()) {
			token = tokenizer.next();

			// check if the component to add is a java class
			if (token.equals(JAVA_CLASS)) {
				new InterpreterAddLazyJavaClass(tokenizer, panel);
			} else {
				if (tokenizer.hasNext()) {
					nextToken = tokenizer.next();
					// add class name and find class component
					if (nextToken.equals("-c")) {
						specified = true;
						if (tokenizer.hasNext()) {
							className = tokenizer.next();
							if (ComponentMap.map.containsKey(className)
									|| ComponentMap.map.get(className) instanceof ClassPaintComponent) {
								classComp = (ClassPaintComponent) ComponentMap.map.get(className);
							} else {
								throw new ExecutionErrorException("class not found");
							}
						} else {
							throw new ExecutionErrorException("invalid script");
						}

						// get component name
						if (tokenizer.hasNext()) {
							nextToken = tokenizer.next();
						}
					}

					// add component name
					compName = nextToken;
				}

				// if class name is not specified, use the selected class component
				if (!specified) {
					ArrayList<PaintComponent> comps = panel.getSelectTool().getSelectedComponents();
					if (comps.size() == 1 && comps.get(0) instanceof ClassPaintComponent) {
						classComp = (ClassPaintComponent) comps.get(0);
					} else {
						throw new ExecutionErrorException("class not found");
					}
				}

				switch (token) {
				case JAVA_CONSTRUCTOR:
					comp = performAddJavaConstructorAction();
					break;

				case JAVA_FIELDS_COMPONENT:
					comp = performAddJavaFieldsComponentAction();
					break;

				case JAVA_METHOD_COMPONENT:
					comp = performAddJavaMethodComponentAction();
					break;

				default:
					throw new ExecutionErrorException("invalid script");
				}

				// name and store the component added
				if (comp != null) {
					ComponentMap.map.put(compName, comp);
				}
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private PaintComponent performAddJavaClassAction() {
		String className = JOptionPane.showInputDialog("Please specify the name of the Java Class");
		try {
			Class classObj = Class.forName(className);
			ClassPaintComponent comp = new ClassPaintComponent(classObj, panel.getWidth() / 2, panel.getHeight() / 2);
			panel.addPaintComponent(comp);

			panel.repaint();
			return comp;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, className + " :: Class Not Found");
			return null;
		}
	}

	private PaintComponent performAddJavaConstructorAction() {
		Constructor[] cons = classComp.getDisplayingClass().getConstructors();

		int desiaredConstructorIndex = Integer.parseInt(JOptionPane.showInputDialog(
				"Please enter the index of the constructor you would like to use: \n\n\n" + getConstructorsSelectionUI(cons)));
		ClassConstructorPaintComponent consComp = new ClassConstructorPaintComponent(cons[desiaredConstructorIndex],
				panel.getWidth() / 2, panel.getHeight() / 2);
		panel.addPaintComponent(consComp);

		panel.repaint();
		return consComp;
	}

	private String getConstructorsSelectionUI(Constructor[] cons) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cons.length; i++) {
			Constructor constructor = cons[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();
	}

	private PaintComponent performAddJavaFieldsComponentAction() {
		FieldsPaintComponent fieldsComp = new FieldsPaintComponent(classComp.getDisplayingClass(), panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(fieldsComp);

		panel.repaint();
		return fieldsComp;
	}

	private PaintComponent performAddJavaMethodComponentAction() {
		Method[] methods = classComp.getDisplayingClass().getMethods();

		int desiaredConstructorIndex = Integer.parseInt(JOptionPane.showInputDialog(
				"Please enter the index of the constructor you would like to use: \n\n\n" + getMethodsSelectionUI(methods)));
		MethodPaintComponent methodComp = new MethodPaintComponent(methods[desiaredConstructorIndex], panel.getWidth() / 2,
				panel.getHeight() / 2);
		panel.addPaintComponent(methodComp);

		panel.repaint();
		return methodComp;
	}

	public String getMethodsSelectionUI(Method[] methods) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < methods.length; i++) {
			Method constructor = methods[i];
			builder.append(i + " : " + constructor.toString() + "\n");
		}
		return builder.toString();
	}
}
