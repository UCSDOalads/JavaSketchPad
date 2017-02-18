package painttools.toolbar;
import java.awt.Button;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.tools.Tool;

import painttools.tools.DotTool;
import painttools.tools.PaintTool;


public class ToolBar extends JPanel {
	
	
	public ArrayList<ToolBarListener> listeners;
	
	public ToolBar(){
		listeners = new ArrayList<>();
		addTool(new DotTool());
	}

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
	
	public void addToolBarListener(ToolBarListener listener){
		listeners.add(listener);
	}
	
	private void select(PaintTool tool){
		for (ToolBarListener toolBarListener : listeners) {
			toolBarListener.toolSelected(tool);
		}
	}

}
