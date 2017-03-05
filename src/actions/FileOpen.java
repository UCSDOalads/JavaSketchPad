package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import actions.menu.ActionsMenuBarTitles;
import file.PanelIO;
import ui.PaintPanel;

public class FileOpen extends PaintAction {

	public FileOpen(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String filePath = JOptionPane.showInputDialog("Please input file path");
		PanelIO io = new PanelIO();
		try {
			io.constructPanelFromDocument(panel, filePath, true);
			panel.repaint();
		} catch (ParserConfigurationException | SAXException | IOException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.File().Open().toString();
	}

}
