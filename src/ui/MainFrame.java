package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import actions.ActionsMenuBar;
import painttools.toolbar.ToolBar;

public class MainFrame extends JFrame{
	
	
	public MainFrame(){

		//set up toolbar and main panel
		PaintPanel paintPanel = new PaintPanel();
		ToolBar toolBar = new ToolBar(paintPanel);
		
		//link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());
		
		//menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		toolBar.getSelectTool().addSelectionToolListener(menuBar);




		//set background
		paintPanel.setBackground(Color.WHITE);

		//link toolbar with mainPanel
		toolBar.addToolBarListener(paintPanel);
		
		//add components
		add(toolBar, BorderLayout.WEST);
		add(paintPanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);
		
		
		setSize(new Dimension(500, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}

}
