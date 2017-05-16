package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import actions.menu.ActionsMenuBar;
import painttools.toolbar.ToolBar;
import ui.helper.historyui.undoredoLog.UndoredoDialog;

public class MainFrame extends JFrame{
	Color color = new Color(100,100,100);
	
	
	public MainFrame(){
		

		//meke the frame full screen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		setForeground(color);
		setBackground(color);
		getContentPane().setForeground(color);

		//set up toolbar and main panel and undoredo panel
		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setBackground(color);
		ToolBar toolBar = new ToolBar(paintPanel);
		toolBar.setBackground(color);
		UndoredoDialog undoredoDialog = UndoredoDialog.sharedInstance();
		undoredoDialog.setBackground(color);
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(300,MAXIMIZED_VERT));
		westPanel.setBackground(color);
		
		//link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());
		
		//menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		toolBar.getSelectTool().addSelectionToolListener(menuBar);

		
		getContentPane().add(paintPanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);
		
		//
		add(toolBar, BorderLayout.NORTH);
		
		
		
		
		
		//set and add westPanel
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		add(westPanel,BorderLayout.WEST);

		//add westPanel components
		westPanel.add(undoredoDialog);
		toolBar.addToolBarListener(paintPanel);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
	
	


}
