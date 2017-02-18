package painttools.toolbar;

import java.awt.Button;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.tools.Tool;

import painttools.tools.DotTool;
import painttools.tools.PaintTool;
import painttools.tools.SelectTool;

public class ToolBar extends JPanel {

	public ArrayList<ToolBarListener> listeners;
	private SelectTool selectTool;

	/**
	 * Creates a default toolbar and add necessary tools
	 */
	public ToolBar() {
		listeners = new ArrayList<>();
		
		//sets the box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		selectTool = new SelectTool();
		addTool(new DotTool());
		addTool(selectTool);
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
