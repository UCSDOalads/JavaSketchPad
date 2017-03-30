package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import actions.menu.ActionsMenuBarTitles;
import file.PanelIO;
import ui.PaintPanel;

import javax.swing.JFileChooser;
import java.io.File;

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
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select the file to open");
		int userSelection = fileChooser.showOpenDialog(panel);
		
		String filePath;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    filePath = fileToSave.getAbsolutePath();
		} else {
			return;
		}
		
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
