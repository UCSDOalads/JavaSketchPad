package painttools.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import buttons.ToolButton;

import painttools.tools.AddClassTool;
import painttools.tools.AddInputBoxTool;
import painttools.tools.AddOutputBoxTool;
import painttools.tools.DotTool;
import painttools.tools.LineTool;
import painttools.tools.PaintTool;
import painttools.tools.SelectTool;
import ui.PaintPanel;
import ui.ShortcutHandler;

public class ToolBar extends JPanel {

	public ArrayList<ToolBarListener> listeners;
	private SelectTool selectTool;
	public ArrayList<ToolButton> buttons;
	private PaintPanel panel;
	
	/**
	 * Creates a default toolbar and add necessary tools
	 */
	public ToolBar(PaintPanel panel) {
		listeners = new ArrayList<>();
		buttons = new ArrayList<>();
		this.panel = panel;
		
		//sets the box layout
		setLayout(new FlowLayout());
		
		selectTool = new SelectTool(panel);
		addTool(new DotTool());
		addTool(selectTool);


		addTool(new AddClassTool(panel));
		addTool(new AddInputBoxTool(panel));
		addTool(new AddOutputBoxTool(panel));

		addTool(new LineTool());

		addSeprator();

		
		this.addKeyListener(new ShortcutHandler(panel));
	}

	
	/**
	 * Adds a tool to the toolbar. This method will add specific tool to the
	 * tool bar, and an action listener associated with it
	 * 
	 * @param tool
	 */
	private void addTool(PaintTool tool) {
		ToolButton button = tool.getButton();
		buttons.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonSelection(e);
				select(tool);
			}
		});
		addSeprator();
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
	
	/**
	 * this method will set one selected button, and rest buttons 
	 * in the tool bar to be unselected
	 * @param e
	 */
	public void setButtonSelection(ActionEvent e){
		for(ToolButton button: buttons){
			if(!button.equals(e.getSource())){
				button.setSelected(false);
			}
			else{
				button.setSelected(true);
			}
		}
	}
	
	public void addSeprator(){
		JSeparator j = new JSeparator(SwingConstants.VERTICAL);
		j.setBackground(Color.white);
		j.setPreferredSize(new Dimension(5,33));
		add(j);
	}

}
