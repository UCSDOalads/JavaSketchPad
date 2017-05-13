package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import actions.menu.ActionsMenuBar;
import painttools.toolbar.ToolBar;
import ui.helper.historyui.undoredoLog.UndoredoDialog;

public class MainFrame extends JFrame{
	
	
	public MainFrame(){
		

		//meke the frame full screen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		getContentPane().setForeground(Color.BLACK);

		//set up toolbar and main panel and undoredo panel
		PaintPanel paintPanel = new PaintPanel();
		ToolBar toolBar = new ToolBar(paintPanel);
		UndoredoDialog undoredoDialog = UndoredoDialog.sharedInstance();
		JPanel westPanel = new JPanel();
		
		//link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());
		
		//menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		toolBar.getSelectTool().addSelectionToolListener(menuBar);

		
		getContentPane().add(paintPanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);
		
		//set and add westPanel
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		getContentPane().add(westPanel,BorderLayout.WEST);

		//add westPanel components
		westPanel.add(toolBar, BorderLayout.WEST);
		westPanel.add(undoredoDialog);
		toolBar.addToolBarListener(paintPanel);
		
		//change theme
		changeTheme();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
	
	
	
	public void changeTheme(){
		//getContentPane().setBackground(new Color(82,86,90));
		getContentPane().setBackground(new Color(80,120,160));
	}

}
