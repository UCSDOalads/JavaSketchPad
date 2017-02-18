package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import painttools.toolbar.ToolBar;

public class MainFrame extends JFrame{
	
	
	public MainFrame(){

		//set up toolbar and main panel
		ToolBar toolBar = new ToolBar();

		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setBackground(Color.WHITE);
		toolBar.addToolBarListener(paintPanel);
		add(toolBar, BorderLayout.WEST);
		add(paintPanel, BorderLayout.CENTER);
		setSize(new Dimension(500, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}

}
