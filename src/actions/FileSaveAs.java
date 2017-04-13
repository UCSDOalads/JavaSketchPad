package actions;

import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import actions.menu.ActionsMenuBarTitles;
import file.PanelIO;
import ui.PaintPanel;

import javax.swing.JFileChooser;
import java.io.File;

public class FileSaveAs extends MenuBarPaintAction {

	public FileSaveAs(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}
	
	/**
	 * Use a JFileChooser to save the file
	 * @author xy gong
	 */
	@Override
	public void performAction() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(panel);
		
		String filePath;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    filePath = fileToSave.getAbsolutePath();
		} else {
			return;
		}
		
		PanelIO io = new PanelIO();
		try {
			io.constructDocumentFromPanel(panel, filePath);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.File().Save().toString();
	}

}
