package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import actions.menu.ActionsMenuBar;
import painttools.toolbar.ToolBar;
import ui.helper.historyui.undoredoLog.UndoredoDialog;

public class JavaSketchPad extends JFrame {
	Color backgroundColor = new Color(240, 240, 240);
	Color toolBarColor = new Color(210,210,210);
	
	ArrayList<JPanel> panelList;

	public JavaSketchPad() {
		panelList = new ArrayList<JPanel>();

		// meke the frame full screen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		//set menu bar to Mac menuBar
		System.setProperty("apple.laf.useScreenMenuBar", "true");


		// set up paintPanel
		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setBorder(null);
		paintPanel.setBackground(backgroundColor);
		panelList.add(paintPanel);
		getContentPane().add(paintPanel, BorderLayout.CENTER);

		// set up toolBar panel
		ToolBar toolBar = new ToolBar(paintPanel);
		toolBar.setBackground(toolBarColor);
		panelList.add(toolBar);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		// set up undoredoDialog panel
		UndoredoDialog undoredoDialog = UndoredoDialog.sharedInstance();
		undoredoDialog.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));
		panelList.add(undoredoDialog);
		getContentPane().add(undoredoDialog, BorderLayout.WEST);

		// link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());

		// menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		menuBar.setOpaque(true);
		menuBar.setFont(new Font("Apple LiSung", Font.PLAIN, 14));
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.GRAY);
		setJMenuBar(menuBar);

		// tool bar
		toolBar.getSelectTool().addSelectionToolListener(menuBar);
		toolBar.addToolBarListener(paintPanel);
		toolBar.setBorder(BorderFactory.createLineBorder(Color.black));

		// change backgrounds setting
		changeTheme();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		paintPanel.toolSelected(paintPanel.getSelectTool());
	}

	public static void main(String[] args) {
		new JavaSketchPad().setVisible(true);
	}

	/* change the background setting for JPanels */
	public void changeTheme() {
		setForeground(backgroundColor);
		setBackground(backgroundColor);
	}

}
