package painttools.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


import painttools.tools.AddClassTool;
import painttools.tools.AddInputBoxTool;
import painttools.tools.AddMethodTool;
import painttools.tools.AddOutputBoxTool;
import painttools.tools.DotTool;
import painttools.tools.PaintTool;
import painttools.tools.SelectTool;

import ui.PaintPanel;

public class ToolBar extends JPanel {

	public ArrayList<ToolBarListener> listeners;
	private SelectTool selectTool;
	private PaintPanel panel;
	
	/**
	 * Creates a default toolbar and add necessary tools
	 */
	public ToolBar(PaintPanel panel) {
		listeners = new ArrayList<>();
		this.panel = panel;
		
		//sets the box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		selectTool = new SelectTool(panel);
		addTool(new DotTool());
		addTool(selectTool);

		addTool(new AddClassTool(panel));
		addTool(new AddInputBoxTool(panel));
		addTool(new AddOutputBoxTool(panel));
		addTool(new LineTool());

	}

	/**
	 * Adds a tool to the toolbar. This method will add specific tool to the
	 * tool bar, and an action listener associated with it
	 * 
	 * @param tool
	 */
	private void addTool(PaintTool tool) {
		JButton button = tool.getButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				select(tool);
			}
		});
		add(button);
	}

	/**
	 * Adds a ToolBarListener to this toolbar
	 * @param listener
	 */
	public void addToolBarListener(ToolBarListener listener) {
		listeners.add(listener);
	}

	/**
	 * this method should be invoked by the actionlistener when an tool button is selected
	 * @param tool
	 */
	private void select(PaintTool tool) {
		for (ToolBarListener toolBarListener : listeners) {
			toolBarListener.toolSelected(tool);
		}
	}

	public SelectTool getSelectTool() {
		return selectTool;
	}
	
	
}
