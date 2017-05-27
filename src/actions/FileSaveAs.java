package actions;

import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.FileOpenGlobalAction;
import actions.global.globalactions.FileSaveAsGlobalAction;
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
		
		FileSaveAsGlobalAction associatedAction 
		= (FileSaveAsGlobalAction) ActionName.FILE_SAVE_AS_GLOBAL_ACTION
				.getAssociatedAction();
		associatedAction.setFilePath(filePath);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.File().Save().toString();
	}

}
