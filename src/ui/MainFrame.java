package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import actions.menu.ActionsMenuBar;
import painttools.toolbar.ToolBar;
import ui.helper.historyui.undoredoLog.UndoredoDialog;

public class MainFrame extends JFrame{
	Color color = new Color(100,100,100);
	ArrayList<JPanel> panelList;
	
	
	public MainFrame(){
		panelList = new ArrayList<JPanel>();
		

		//meke the frame full screen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		setForeground(color);
		setBackground(color);

		//set up paintPanel
		PaintPanel paintPanel = new PaintPanel();
		panelList.add(paintPanel);
		getContentPane().add(paintPanel, BorderLayout.CENTER);
		
		//set up toolBar panel
		ToolBar toolBar = new ToolBar(paintPanel);
		panelList.add(toolBar);
		add(toolBar, BorderLayout.NORTH);
		
		//set up undoredoDialog panel
		UndoredoDialog undoredoDialog = UndoredoDialog.sharedInstance();
		undoredoDialog.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));
		panelList.add(undoredoDialog);
		add(undoredoDialog,BorderLayout.WEST);
		
		
		//link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());
		
		//menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		setJMenuBar(menuBar);
		
		//tool bar
		toolBar.getSelectTool().addSelectionToolListener(menuBar);
		toolBar.addToolBarListener(paintPanel);

		

		//change backgrounds setting
		changeTheme();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
	
	
	
	/* change the background setting for JPanels */
	public void changeTheme(){
		for(JPanel panel: panelList){
			panel.setBackground(color);
		}
	}
	
	


}
