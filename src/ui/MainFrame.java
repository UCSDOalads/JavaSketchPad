package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import painttools.toolbar.ToolBar;
import ui.helper.historyui.undoredoLog.UndoredoDialog;
import actions.menu.ActionsMenuBar;

public class MainFrame extends JFrame {

	public MainFrame() {

		// set up toolbar and main panel and undoredo panel
		PaintPanel paintPanel = new PaintPanel();
		ToolBar toolBar = new ToolBar(paintPanel);
		UndoredoDialog undoredoDialog = UndoredoDialog.sharedInstance();
		JPanel westPanel = new JPanel();

		// link select tool
		paintPanel.setSelectTool(toolBar.getSelectTool());

		// menubar
		ActionsMenuBar menuBar = new ActionsMenuBar(paintPanel);
		toolBar.getSelectTool().addSelectionToolListener(menuBar);

		// set background
		paintPanel.setBackground(Color.WHITE);

		add(paintPanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);

		// set and add westPanel
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		add(westPanel, BorderLayout.WEST);

		// add westPanel components
		westPanel.add(toolBar, BorderLayout.WEST);
		westPanel.add(undoredoDialog);
		toolBar.addToolBarListener(paintPanel);

		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		paintPanel.toolSelected(paintPanel.getSelectTool());
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}
